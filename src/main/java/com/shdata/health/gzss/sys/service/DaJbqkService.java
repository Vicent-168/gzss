package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.common.feign.ZacianService;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.utils.DataUtil;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.sys.entity.DaJbqk;
import com.shdata.health.gzss.sys.entity.DaJbqkll;
import com.shdata.health.gzss.sys.mapper.DaJbqkMapper;
import com.shdata.health.gzss.sys.mapper.DaJbqkllMapper;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.bo.BsMxbBo;
import com.shdata.health.gzss.sys.vo.bo.SfXshjcBo;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 档案基本情况  Service服务
 *
 * @author xgb
 * @date 2024-07-10
 */
@Service
@Slf4j
@Transactional
public class DaJbqkService extends BaseService<DaJbqkMapper, DaJbqk> {

    @Autowired
    private DaJbqkMapper daJbqkMapper;

    @Autowired
    private DaJbqkllMapper daJbqkllMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private ZacianService zacianService;

    @Autowired
    private BsJwgzsService bsJwgzsService;
    @Autowired
    private BsMxbService bsMxbService;
    @Autowired
    private BsMxbFysService bsMxbFysService;
    @Autowired
    private BsYjqkService bsYjqkService;
    @Autowired
    private SfFyxxService sfFyxxService;
    @Autowired
    private SfGmdjcService sfGmdjcService;
    @Autowired
    private SfXshjcService sfXshjcService;

    /**
     * 基础信息表——保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DaJbqkVo vo) {

        validate(vo);
        DaJbqk po = null;
        if (StrUtil.isNotBlank(vo.getDaId())) {
            po = getById(vo.getDaId());
        }
        if (po == null) { //新增
            // 唯一性校验：检查身份证号是否已经存在
            List<DaJbqk> daJbqks = this.baseMapper.selectList(new LambdaQueryWrapper<DaJbqk>()
                    .eq(StrUtil.isNotBlank(vo.getSfzh()), DaJbqk::getSfzh, vo.getSfzh())
                    .eq(DaJbqk::getDelFlag, "0")
            );
            if (daJbqks.size() > 0) {
                throw new RuntimeException("该身份证号已存在，不能重复添加");
            }
            // 计算BMI并确定bmisp的值
            vo.calculateBmiAndDetermineBmisp();
            //档案登记时间
            vo.setDadjsj(new java.util.Date());
            //根据身份证号生成出生日期和性别
            try {
                vo.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            po = BeanUtil.toBean(vo, DaJbqk.class);
            po.setDaId(IdUtil.objectId());
            po.init();
            save(po);
            //将新增的档案信息插入到履历表
            DaJbqkll daJbqkll = new DaJbqkll();
            daJbqkll.setLlid(IdUtil.objectId());
            BeanUtil.copyProperties(po, daJbqkll);
            daJbqkllMapper.insert(daJbqkll);
            return "保存成功";
        } else {
            //档案状态改变时间
            vo.setDaztdjsj(new java.util.Date());
            BeanUtil.copyProperties(vo, po);
            po.initByUpdate();
            //患者档案信息修改维护到档案表
            updateById(po);
            //患者档案信息修改记录插入到履历表
            DaJbqkll daJbqkll = new DaJbqkll();
            daJbqkll.setLlid(IdUtil.objectId());
            BeanUtil.copyProperties(po, daJbqkll);
            daJbqkllMapper.insert(daJbqkll);
            return "更新成功";
        }
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(DaJbqkVo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }

    }

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<DaJbqkVo> findByPage(DaJbqkSearchVo search) {
        IPage<DaJbqkVo> page = daJbqkMapper.findByPage(search);
        return PageData.of(page);
    }


    //@Override
    public List<DaJbqk> findByKeyword(String keyword) {
        return daJbqkMapper.selectByKeyword(keyword);
    }

    //@Override
    public DaJbqk findByUniqueKeyword(String keyword) {
        return daJbqkMapper.selectByUniqueKeyword(keyword);
    }

    //根据档案id查询患者基本情况
    public DaJbqkVo getDaJbqkByDaId(String daId) {

        DaJbqkVo daJbqkVo = daJbqkMapper.findByDaId(daId);
        if (daJbqkVo == null) {
            return new DaJbqkVo();

        } else {
            String mxjb = daJbqkVo.getMxjb();
            String mxjbName=convertMxjbToMxjbName(mxjb,"MXJB");
            daJbqkVo.setMxjbName(mxjbName);
            String mxjbywzl = daJbqkVo.getMxjbywzl();
            String mxjbywzlName=convertMxjbToMxjbName(mxjbywzl,"MXJBYWZL");
            daJbqkVo.setMxjbywzlName(mxjbywzlName);
            String yjzl = daJbqkVo.getYjzl();
            String yjzlName=convertMxjbToMxjbName(yjzl,"YJQK");
            daJbqkVo.setYjzlName(yjzlName);
            String gzpg = daJbqkVo.getGzpg();
            String gzpgName=convertMxjbToMxjbName(gzpg,"GZPG");
            daJbqkVo.setGzpgName(gzpgName);
            daJbqkVo.updateJzdz();
            // 根据身份证号设置出生日期和年龄
            try {
                daJbqkVo.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return daJbqkVo;

    }

    public String convertMxjbToMxjbName(String codelist, String type) {
        // 去除前后引号并分割成数组
        String[] codes = codelist.replace("'", "").split(",");
        List<String> convertedNames = new ArrayList<>();

        // 遍历每个code，进行字典值转换
        for (String code : codes) {
            String dictName = getDictName(type, code);
            convertedNames.add(dictName != null ? dictName : code);  // 如果字典没有匹配到，保留原值
        }

        // 将转换后的结果拼接回去，用逗号分隔
        return String.join(",", convertedNames);
    }

    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 根据病员号获取档案ID
     *
     * @param patientNumber 病员号
     * @return 档案ID，如果找不到返回 null
     */
    public String getDaIdByPatientNumber(String patientNumber) {
        // 参数检查
        if (patientNumber == null || patientNumber.isEmpty()) {
            throw new IllegalArgumentException("病员号不能为空");
        }

        // 从数据源中获取档案ID
        String daId = daJbqkMapper.getArchiveIdByPatientNumber(patientNumber);

        // 返回结果
        return daId;
    }

    /**
     * 档案管理-档案一览
     */
    public PageData<DaJbqkRespVo> getDaDataByCriterias(DaJbqkSearchVo vo) {
        if (vo.getGlyljg() == null||vo.getGlyljg().isEmpty()) {
            vo.setGlyljg(UserUtils.getOrgan().getCode());
        }
        //执行查询
        IPage<DaJbqkRespVo> iPage = daJbqkMapper.findDataByCriterias(vo);
        if (iPage.getRecords() != null) {
            // 字典值转换
            List<String> jtysIds = new ArrayList<>();
            List<String> zsIds = new ArrayList<>();
            for (DaJbqkRespVo item : iPage.getRecords()) {
                jtysIds.add(item.getJtysid());
                zsIds.add(item.getSfzsid());
                // 根据身份证号设置出生日期和年龄
                try {
                    item.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    e.printStackTrace();

                }
                // 拼接居住地址
                item.updateJzdz();
            }
            Map<String,String> jtysMap = DataUtil.getUserNameForMap(jtysIds);
            Map<String,String> sfzsMap = DataUtil.getUserNameForMap(zsIds);
            for (DaJbqkRespVo record : iPage.getRecords()) {
                record.setJtys(jtysMap.get(record.getJtysid()));
                record.setSfzs(sfzsMap.get(record.getSfzsid()));
            }

        }
        return PageData.of(iPage);
    }

    /**
     * 通过身份证号获取出生日期、性别、年龄
     */
    public PersonalVo getPsersonalInfo(String sfzh) {
        if (sfzh == null || sfzh.length() != 18) {
            throw new IllegalArgumentException("身份证号格式不正确");
        }
        PersonalVo personalVo = new PersonalVo();
        // 从身份证号中提取出生日期
        String birthDateStr = sfzh.substring(6, 14);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            throw new RuntimeException("出生日期解析失败", e);
        }

        // 计算年龄
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);
        int birthYear = birthCalendar.get(Calendar.YEAR);
        int birthMonth = birthCalendar.get(Calendar.MONTH);
        int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar nowCalendar = Calendar.getInstance();
        int currentYear = nowCalendar.get(Calendar.YEAR);
        int currentMonth = nowCalendar.get(Calendar.MONTH);
        int currentDay = nowCalendar.get(Calendar.DAY_OF_MONTH);

        int age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--;
        }

        // 赋值给字段
        personalVo.setCsrq(birthDate);
        personalVo.setNl(age);
        // 提取性别信息
        char genderCode = sfzh.charAt(16);
        personalVo.setXb((genderCode % 2 == 0) ? "2" : "1");

        return personalVo;
    }

    /**
     * 根据身高和体重计算BMI
     */
    public PersonalBmiInfoVo getPsersonalBmiInfo(PersonalBmiVo vo) {
        String bmisp = null; // 处理无效输入
        if (vo.getSg() == null || vo.getTz() == null || vo.getSg().compareTo(BigDecimal.ZERO) == 0) {
            bmisp = null; // 处理无效输入
            throw new IllegalArgumentException("身高或体重不能为空");
        }
        PersonalBmiInfoVo personalBmiInfoVo = new PersonalBmiInfoVo();
        // 将身高从cm转换为m
        BigDecimal heightInMeters = vo.getSg().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        // 计算BMI值
        BigDecimal bmi = vo.getTz().divide(heightInMeters.multiply(heightInMeters), 2, RoundingMode.HALF_UP);
        personalBmiInfoVo.setBMiSz(bmi.toString());
        // 确定bmisp的值
        if (bmi.compareTo(new BigDecimal("18.5")) < 0) {
            bmisp = "01"; // BMI < 18.5
            personalBmiInfoVo.setBmisp(bmisp);
        } else if (bmi.compareTo(new BigDecimal("24")) < 0) {
            bmisp = "02"; // 18.5 <= BMI < 24
            personalBmiInfoVo.setBmisp(bmisp);
        } else if (bmi.compareTo(new BigDecimal("28")) < 0) {
            bmisp = "03"; // 24 <= BMI < 28
            personalBmiInfoVo.setBmisp(bmisp);
        } else {
            bmisp = "04"; // BMI >= 28
            personalBmiInfoVo.setBmisp(bmisp);
        }
        return personalBmiInfoVo;
    }

    /**
     * 通过身份证号查询档案基本情况
     */
    public DaJbqkVo getDaJbqkBySfzh(String sfzh) {
        DaJbqkVo daJbqkVo = daJbqkMapper.findBySfzh(sfzh);
        if (daJbqkVo == null) {
            return new DaJbqkVo();

        } else {
            String mxjb = daJbqkVo.getMxjb();
            String mxjbName=convertMxjbToMxjbName(mxjb,"MXJB");
            daJbqkVo.setMxjbName(mxjbName);
            String mxjbywzl = daJbqkVo.getMxjbywzl();
            String mxjbywzlName=convertMxjbToMxjbName(mxjbywzl,"MXJBYWZL");
            daJbqkVo.setMxjbywzlName(mxjbywzlName);
            String yjzl = daJbqkVo.getYjzl();
            String yjzlName=convertMxjbToMxjbName(yjzl,"YJQK");
            daJbqkVo.setYjzlName(yjzlName);
            String gzpg = daJbqkVo.getGzpg();
            String gzpgName=convertMxjbToMxjbName(gzpg,"GZPG");
            daJbqkVo.setGzpgName(gzpgName);
            //居住地址省的字典值转换
            daJbqkVo.updateJzdz();
            // 根据身份证号设置出生日期和年龄
            try {
                daJbqkVo.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return daJbqkVo;
    }

    /**
     * 通过daId逻辑删除档案基本情况
     */
    @Transactional(rollbackFor = Exception.class)
    public String deleteByDaId(String daId) {
        try {
            DaJbqkVo daJbqkVo = daJbqkMapper.findByDaId(daId);
            if (daJbqkVo == null) {
                log.info("档案信息未找到: {}", daId);
                return "档案信息未找到";
            }

            // 对基础信息表的逻辑删除
            deleteById(daId);
            daJbqkVo.setDelFlag("1");

            // 确保daJbqkVo不为空再进行复制
            if (daJbqkVo != null) {
                // 患者档案信息修改记录插入到履历表
                DaJbqkll daJbqkll = new DaJbqkll();
                daJbqkll.setLlid(IdUtil.objectId());
                BeanUtil.copyProperties(daJbqkVo, daJbqkll);
                daJbqkllMapper.insert(daJbqkll);
                daJbqkllMapper.deleteById(daJbqkll.getLlid());
            } else {
                log.error("daJbqkVo is null for daId: {}", daId);
                return "内部错误";
            }

            log.info("删除成功: {}", daId);
            return "删除成功";

        } catch (Exception e) {
            log.error("删除档案时出现异常: {}", e.getMessage(), e);
            throw e; // 让Spring事务管理器回滚事务
        }
    }

    /**
     * 档案登记的新增和修改
     *
     * @param
     * @return
     */
    @Transactional
    public String saveOrUpdateAll(DaAllVo daAllVo) {
        DaJbqkVo daJbqkVo = daAllVo.getDaJbqkVo();
        if (daJbqkVo != null) {
            validate(daJbqkVo);
            DaJbqk po = null;
            if (StrUtil.isNotBlank(daJbqkVo.getDaId())) {
                po = getById(daJbqkVo.getDaId());
            }
            if (po == null) { //新增
                // 唯一性校验：检查身份证号是否已经存在
               /*  List<DaJbqk> daJbqks = this.baseMapper.selectList(new LambdaQueryWrapper<DaJbqk>()
                        .eq(StrUtil.isNotBlank(daJbqkVo.getSfzh()), DaJbqk::getSfzh, daJbqkVo.getSfzh())
                        .eq(DaJbqk::getDelFlag, "0")
                );
                if (daJbqks.size() > 0) {
                    throw new RuntimeException("该身份证号已存在，不能重复添加");
                } */
                // 计算BMI并确定bmisp的值
                daJbqkVo.calculateBmiAndDetermineBmisp();
                //档案登记时间只保存年月日
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date onlyDate = null; // 格式化并解析为日期
                try {
                    onlyDate = sdf.parse(sdf.format(new Date()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                daJbqkVo.setDadjsj(onlyDate);
                //根据身份证号生成出生日期和性别
                try {
                    daJbqkVo.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    log.error("解析身份证号失败", e);
                    throw new RuntimeException("身份证号码格式错误，请检查后重试");
                }
                po = BeanUtil.toBean(daJbqkVo, DaJbqk.class);
                po.setDaId(IdUtil.objectId());
                po.setGljg(UserUtils.getOrgan().getCode());
                po.init();
                save(po);
                //将新增的档案信息插入到履历表
                DaJbqkll daJbqkll = new DaJbqkll();
                daJbqkll.setLlid(IdUtil.objectId());
                BeanUtil.copyProperties(po, daJbqkll);
                daJbqkllMapper.insert(daJbqkll);
            } else {
                //档案状态改变时间
                daJbqkVo.setDaztdjsj(new java.util.Date());
                BeanUtil.copyProperties(daJbqkVo, po);
                po.initByUpdate();
                //患者档案信息修改维护到档案表
                updateById(po);
                //患者档案信息修改记录插入到履历表
                DaJbqkll daJbqkll = new DaJbqkll();
                daJbqkll.setLlid(IdUtil.objectId());
                BeanUtil.copyProperties(po, daJbqkll);
                daJbqkllMapper.insert(daJbqkll);
            }
            String daId = po.getDaId();
            // Step 2: 使用生成的 daId 更新其他小表单
            if (daId != null) {
                updateSubTables(daId, daAllVo);
            }
        }
        return "保存成功";
    }

    private void updateSubTables(String daId, DaAllVo daAllVo) {
        //存在既往骨折就会对应去更新对应的表单，没有就跳过
        if ("1".equals(daAllVo.getDaJbqkVo().getJwgzs())) {
            //更新既往骨折史
            daAllVo.getBsJwgzsBo().setDaId(daId);
            bsJwgzsService.saveOrUpdate(daAllVo.getBsJwgzsBo());
        }
        String mxjb=daAllVo.getDaJbqkVo().getMxjb();
        if (!mxjb.contains("00")) {
            // 更新病史慢性病史
            BsMxbBo bsMxbBo = daAllVo.getBsMxbBo();
            bsMxbBo.setDaId(daId);
            bsMxbService.saveOrUpdate(bsMxbBo);
        }
        String mxjbywzl=daAllVo.getDaJbqkVo().getMxjbywzl();
        if (!mxjbywzl.contains("00")) {
            //更新病史慢性病服药史
            daAllVo.getBsMxbFysBo().setDaId(daId);
            bsMxbFysService.saveOrUpdate(daAllVo.getBsMxbFysBo());
        }
        String yjzl=daAllVo.getDaJbqkVo().getYjzl();
        if (!yjzl.contains("00")) {
            //更新病史饮酒史
            daAllVo.getBsYjqkBo().setDaId(daId);
            bsYjqkService.saveOrUpdate(daAllVo.getBsYjqkBo());
        }
        List<SfFyDjxxVo> sfFyxxVoList = daAllVo.getSfFyxxBo().getSfFyxxVoList();
        if (sfFyxxVoList != null && sfFyxxVoList.size() > 0) {
            //更新慢性病服药信息表
            daAllVo.getSfFyxxBo().setDaId(daId);
            sfFyxxService.saveOrUpdate(daAllVo.getSfFyxxBo());
        }
        SfGmdjcVo sfGmdjcVo = daAllVo.getSfGmdjcVo();
        if (sfGmdjcVo != null && sfGmdjcVo.getJcyljg()!="" && sfGmdjcVo.getDxaJcrq()!=null) {
            //更新随访骨密度
            daAllVo.getSfGmdjcVo().setDaId(daId);
            sfGmdjcService.saveOrUpdate(daAllVo.getSfGmdjcVo());
        }
        SfXshjcRequestVo request = daAllVo.getRequest();
        /* List<SfXshjcBo> boList = daAllVo.getRequest().getBoList();
        List<SfXshjcVo> sfXshjcVoList = boList.get(0).getSfXshjcVoList();
        if (sfXshjcVoList != null && sfXshjcVoList.size() > 0) {
            //更新随访血生化
            daAllVo.getRequest().setDaId(daId);
            sfXshjcService.saveOrUpdate(daAllVo.getRequest());
        } */
        if (request != null && (request.getBoList().get(0).getJyrq() != null&&request.getBoList().get(0).getJyyljg() != "") && !request.getBoList().get(0).getSfXshjcVoList().isEmpty()) {
            //更新随访血生化
            daAllVo.getRequest().setDaId(daId);
            sfXshjcService.saveOrUpdate(daAllVo.getRequest());
        }
    }
    /**
     * 通过档案ID查询个人简单基本情况
     */
    public PersonalInfoVo getPsersonalInfoByDaId(String daId) {

        if (daId == null) {
            throw new ParameterException("档案ID不能为空");
        }

        PersonalInfoVo personalInfoVo=daJbqkMapper.findPsersonalInfoByDaId(daId);
        if (personalInfoVo!=null) {
            try {
                personalInfoVo.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            // 拼接居住地址
            personalInfoVo.updateJzdz();
        }


        return personalInfoVo;

    }
    /**
     * 通过姓名/身份证号/社保卡号查询个人简单基本情况
     */
    public PersonalInfoVo getPsersonalInfoBKeyword(PersonalSearchVo vo) {
        if (vo.getKeyword() == null) {
            throw new ParameterException("入参不能为空");
        }
        PersonalInfoVo personalInfoVo=daJbqkMapper.getPsersonalInfoBKeyword(vo);
        if (personalInfoVo!=null) {
            try {
                personalInfoVo.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            // 拼接居住地址
            personalInfoVo.updateJzdz();
        }
        return personalInfoVo;
    }
}
