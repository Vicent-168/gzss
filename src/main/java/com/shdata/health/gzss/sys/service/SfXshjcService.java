package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ExcelException;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.service.JyxmSetService;
import com.shdata.health.gzss.common.utils.ExcelDateListener;
import com.shdata.health.gzss.common.utils.ExcelReaderListenerCallback;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.common.vo.JyxmSetVo;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import com.shdata.health.gzss.sys.entity.SfXshjc;
import com.shdata.health.gzss.sys.mapper.DaJbqkMapper;
import com.shdata.health.gzss.sys.mapper.PgJgMapper;
import com.shdata.health.gzss.sys.mapper.SfXshjcMapper;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.bo.SfXshjcBo;
import com.shdata.health.gzss.sys.vo.resp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 随访_血生化检查  Service服务
 *
 * @author dwt
 * @date 2024-07-13
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class SfXshjcService extends BaseService<SfXshjcMapper, SfXshjc> {

    @Autowired
    private SfXshjcMapper sfXshjcMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private DaJbqkMapper daJbqkMapper;

    @Autowired
    private JyxmSetService jyxmSetService;

    @Autowired
    private PgJgMapper pgJgMapper;

    @Autowired
    private PgJgService pgJgService;



    /**
     * 血生化excel检查导入
     */
    @Transactional
    public String upload(MultipartFile file) {


        try (InputStream inputStream = file.getInputStream()) { // 使用try-with-resources确保流关闭
            EasyExcel.read(inputStream, XshjcVo.class, new ExcelDateListener<XshjcVo>((ExcelReaderListenerCallback<XshjcVo>) (data, headMap) -> {
                        // 行号计数器，数据从第二行开始
                        int rowIndex = 1; // 表头占第一行，数据从第二行开始
                        //先校验
                        for (XshjcVo o : data) {
                            try {
                                validate(o);
                            } catch (Exception e) {
                                throw new ParameterException("导入失败：第 " + rowIndex + " 行, " + e.getMessage());
                            }
                            rowIndex++;
                        }
                        //校验通过后保存
                        for (XshjcVo o : data) {
                            saveRecord(o);
                        }
                    }))
                    .sheet().doRead();
        } catch (IOException e) {
            log.error("导入Excel文件异常", e);
            throw new ExcelException("导入Excel文件异常", e);
        }
        return "上传成功";
    }

    /**
     * 血生化excel表单参数校验
     */
    private void validate(XshjcVo record) {
        // 用来记录哪个字段出错
        String errorField = "";
        // 1. 校验必填字段是否为空
        if (record.getPatientNumber() == null || record.getPatientNumber().isEmpty()) {
            throw new ParameterException("病员号不能为空，导入失败！");
        }
        if (record.getTestDate() == null || record.getTestDate().isEmpty()) {
            throw new ParameterException("检验日期不能为空，导入失败！");
        }
        if (record.getResult() == null || record.getResult().isEmpty()) {
            throw new ParameterException("检测结果值不能为空，导入失败！");
        }
        // 2. 校验病员号是否存在
        errorField = "病员号";
        String daId = daJbqkMapper.getArchiveIdByPatientNumber(record.getPatientNumber());
        if (daId == null) {
            throw new ParameterException("病员号 " + record.getPatientNumber() + " 不在档案中，导入失败！");
        }
        // 3. 处理检验日期
        errorField = "检验日期";
        Date testDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(record.getTestDate(), formatter);
            testDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new ParameterException("检验日期格式错误，应为 yyyy-MM-dd 格式，导入失败！");
        }
        // 4. 设置提示字段 为空则正常设置值为01 不为空则为异常设置02
        String hintValue = (record.getHint() != null && !record.getHint().isEmpty()) ? "02" : "01";
        // 创建SfXshjc对象并设置属性
        SfXshjc check = new SfXshjc();
        check.setDaId(daId);
        check.setJyrq(new java.sql.Date(testDate.getTime()));
        //获取医生所在的医疗机构代码
        String code = UserUtils.getOrgan().getCode();
        check.setJyyljg(code);//华阳街道社区卫生服务中心
        check.setGljg(code);
        check.setSfysid(UserUtils.getUserId());
        //先根据检验项目到检查配置表里查询对应的检查种类
        List<JyxmSetVo> jyxmSetVos = jyxmSetService.getJyzlByJyxm(record.getTestItem());
        if (jyxmSetVos.isEmpty()) {
            throw new ParameterException("检验项目 " + record.getTestItem() + " 不在配置中");
        }
        String Jyzl = jyxmSetVos.get(0).getBigCode();
        String Jyxmdm = jyxmSetVos.get(0).getJyXmdm();
        check.setJyzl(Jyzl); // 根据具体的检验种类配置 参照字典转换
        check.setJyxm(Jyxmdm);  //检验项目  字典转换存入
        String xb = (record.getGender() != null && "男".equals(record.getGender())) ? "1" : "2";
        Integer nl = Integer.valueOf(record.getAge());
        //根据性别、年龄、检查项目代码找到对应的参考值
        JyxmSetVo jyxmSetVo = jyxmSetService.findByCriteria(Jyxmdm, xb, nl);
        if (jyxmSetVo == null) {
            throw new ParameterException("未找到符合条件的检验配置");
        }

    }

    /**
     * 血生化excel表单数据保存
     */
    private void saveRecord(XshjcVo record) {
        List<JyxmSetVo> jyxmSetVos = jyxmSetService.getJyzlByJyxm(record.getTestItem());
        String Jyzl = jyxmSetVos.get(0).getBigCode();
        String Jyxmdm = jyxmSetVos.get(0).getJyXmdm();

        String xb = (record.getGender() != null && "男".equals(record.getGender())) ? "1" : "2";
        Integer nl = Integer.valueOf(record.getAge());

        JyxmSetVo jyxmSetVo = jyxmSetService.findByCriteria(Jyxmdm, xb, nl);

        SfXshjc check = new SfXshjc();
        String daId = daJbqkMapper.getArchiveIdByPatientNumber(record.getPatientNumber());
        check.setDaId(daId);
        Date testDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(record.getTestDate(), formatter);
        testDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        check.setJyrq(new java.sql.Date(testDate.getTime()));
        check.setJyzl(Jyzl);
        check.setJyxm(Jyxmdm);
        // 4. 设置提示字段 为空则正常设置值为01 不为空则为异常设置02
        String hintValue = (record.getHint() != null && !record.getHint().isEmpty()) ? "02" : "01";

        String ckz = jyxmSetVo.getJyCkz();//参考值范围
        check.setCkz(ckz);
        check.setTs(hintValue);
        check.setJysz(record.getResult());
        check.setDw(jyxmSetVo.getDw());//单位
        check.setSffs("01");//随访方式默认为门诊 01
        //获取医生所在的医疗机构代码
        String code = UserUtils.getOrgan().getCode();
        check.setSfysid(UserUtils.getUserId());
        check.setSfys(UserUtils.getUser().getName());
        check.setJyyljg(code);//华阳街道社区卫生服务中心
        check.setGljg(code);
        check.setSjly("导入");
        check.setDelFlag("0");
        // 6. 数据库重复校验
        QueryWrapper<SfXshjc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DA_ID", check.getDaId())
                .eq("JYRQ", check.getJyrq())
                .eq("JYXM", check.getJyxm())
                .eq("DEL_FLAG", "0");
        SfXshjc existingRecord = sfXshjcMapper.selectOne(queryWrapper);
        if (existingRecord != null) {
            check.setId(existingRecord.getId());
            check.setUpdateBy(UserUtils.getUserId());
            check.setUpdateTime(new Date());
            sfXshjcMapper.updateById(check);
        } else {
            check.setId(generateId());
            check.setCreateBy(UserUtils.getUserId()); // 获取当前登录用户
            check.setCreateTime(new Date());
            sfXshjcMapper.insert(check);
        }

    }

    private String generateId() {
        // 实现ID生成逻辑
        return IdUtil.objectId();
    }

    /**
     * 保存或更新
     *
     * @param  request
     * @return
     */
    @Transactional
    public String saveOrUpdate(SfXshjcRequestVo request) {
        String daId=request.getDaId();
        List<SfXshjcBo> boList = (List<SfXshjcBo>) request.getBoList();
        Map<String, SfXshjcVo> sfXshMap = new HashMap<>();
        if (boList==null|| boList.isEmpty()) {
            // 查询档案下最新血生化数据
            SfXshxqjcVo sfXshxqjcVo = this.fetchLatestBiochemicalCheckData(daId);
            List<SfXshjcVo> xshjcVoList = sfXshxqjcVo.getXshjcVoList();
            if (CollectionUtil.isNotEmpty(xshjcVoList)) {
                sfXshMap=xshjcVoList.stream().collect(Collectors.toMap(SfXshjcVo::getId, Function.identity()));
            }
            //删除所有得记录
            sfXshMap.keySet().forEach(this.baseMapper::deleteById);
            return "已删除所有血生化记录";
        }

        for (SfXshjcBo sfXshjcBo : boList) {
            Date jyrq = sfXshjcBo.getJyrq();
            String jyyljg = sfXshjcBo.getJyyljg();
            String sffs = sfXshjcBo.getSffs();
            String sfysid = sfXshjcBo.getSfysid();
            String gljg=sfXshjcBo.getGljg();
            List<SfXshjcVo> vos = sfXshjcBo.getSfXshjcVoList();
            //获取档案id下当前检验日期下所有血生化数据
            List<SfXshjc> sfXshjcList = this.baseMapper.selectList(new LambdaQueryWrapper<SfXshjc>().eq(SfXshjc::getDaId, daId).eq(SfXshjc::getJyrq, jyrq).eq(SfXshjc::getJyyljg, jyyljg).eq(SfXshjc::getDelFlag, "0"));
            Map<String, SfXshjc> sfXshjcMap = new HashMap<>();
            if (CollectionUtil.isNotEmpty(sfXshjcList)) {
                sfXshjcMap = sfXshjcList.stream().collect(Collectors.toMap(SfXshjc::getId, Function.identity()));
            }
            if (vos == null || vos.isEmpty() && !sfXshjcMap.isEmpty()) {
                //删除所有得记录
                sfXshjcMap.keySet().forEach(this.baseMapper::deleteById);
                continue; // 继续处理下一个 SfXshjcBo
            }
            sffs =vos.get(0).getSffs();
            sfysid =vos.get(0).getSfysid();
            gljg=vos.get(0).getGljg();
            sfXshjcBo.setSffs(sffs);
            sfXshjcBo.setSfysid(sfysid);
            sfXshjcBo.setGljg(gljg);
            sfXshjcBo.setDaId(daId);
            for (SfXshjcVo sfXshjcVo : vos) {
                sfXshjcVo.setDaId(daId);
                sfXshjcVo.setSffs(sffs);
                sfXshjcVo.setSfysid(sfysid);
                sfXshjcVo.setGljg(gljg);
                String id = sfXshjcVo.getId();
                SfXshjc po = null;
                //xshjcList中存在该记录则更新，不存在则新增
                if (id == null || !sfXshjcMap.containsKey(id)) {
                    /* //新增去重
                    List<SfXshjc> sfXshjcList1 = this.baseMapper.selectList(new LambdaQueryWrapper<SfXshjc>()
                            .eq(StrUtil.isNotBlank(daId), SfXshjc::getDaId, daId)
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getJyyljg()), SfXshjc::getJyyljg, sfXshjcVo.getJyyljg())
                            .eq(sfXshjcVo.getJyrq() != null, SfXshjc::getJyrq, sfXshjcVo.getJyrq())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getJyxm()), SfXshjc::getJyxm, sfXshjcVo.getJyxm())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getDw()), SfXshjc::getDw, sfXshjcVo.getDw())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getJysz()), SfXshjc::getJysz, sfXshjcVo.getJysz())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getTs()), SfXshjc::getTs, sfXshjcVo.getTs())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getGljg()), SfXshjc::getGljg, sfXshjcVo.getGljg())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getSffs()), SfXshjc::getSffs, sfXshjcVo.getSffs())
                            .eq(StrUtil.isNotBlank(sfXshjcVo.getCkz()), SfXshjc::getCkz, sfXshjcVo.getCkz())
                            .eq(SfXshjc::getDelFlag, "0"));
                    if (sfXshjcList1 != null && !sfXshjcList.isEmpty()) {
                        throw new ParameterException("该档案下该检验日期下已存在该血生化记录");
                    } */
                    //执行新增操作
                    po = BeanUtil.toBean(sfXshjcVo, SfXshjc.class);
                    po.setId(IdUtil.objectId());
                    po.setDaId(daId);
                    po.setJyrq(jyrq);
                    po.setJyyljg(jyyljg);
                    po.setGljg(gljg);
                    po.setSffs(sffs);
                    po.setSfysid(sfysid);
                    po.init();
                    save(po);
                } else {
                    po = sfXshjcMap.get(id);
                    if (po == null) {
                        throw new ParameterException("血生化记录不存在");
                    }
                    BeanUtil.copyProperties(sfXshjcVo, po);
                    po.initByUpdate();
                    updateById(po);
                    sfXshjcMap.remove(id);
                }
                //处理删除操作，剩余在map中的记录为需要删除得记录
                if (!sfXshjcMap.isEmpty()) {
                    new ArrayList<>(sfXshjcMap.keySet()).forEach(this.baseMapper::deleteById);
                }
            }
        }
        return "更新成功";
    }

    /**
     * 验证对象
     *
     * @param sfXshjcVo 提交参数
     */
    private void validate(SfXshjcVo sfXshjcVo) {
        if (sfXshjcVo == null) {
            throw new ParameterException("参数不能为空");
        }
        if (StrUtil.isBlank(sfXshjcVo.getDaId())) {
            throw new ParameterException("档案ID不能为空");
        }
        //检验日期不能为空
        if (sfXshjcVo.getJyrq() == null) {
            throw new ParameterException("检验日期不能为空");
        }
        if (sfXshjcVo.getJyyljg() == null) {
            throw new ParameterException("检验机构不能为空");
        }

    }

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<SfXshjcVo> findByPage(SfXshjcSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 查询近期最新血生化数据
     *
     * @return
     */
    public SfXshxqjcVo fetchLatestBiochemicalCheckData(String daId) {
        SfXshxqjcVo sfXshxqjcVo = new SfXshxqjcVo();
        try {
            List<SfXshjcVo> sfXshjcVos = sfXshjcMapper.findLatestBiochemicalCheckData(daId);
            if (sfXshjcVos!=null && !sfXshjcVos.isEmpty()) {
                if (sfXshjcVos.get(0) != null )  {
                    Date jyrq = sfXshjcVos.get(0).getJyrq();
                    String sffs = sfXshjcVos.get(0).getSffs();
                    String sffsName = sfXshjcVos.get(0).getSffsName();
                    String jyyljg = sfXshjcVos.get(0).getJyyljg();
                    String jyyljgmc = sfXshjcVos.get(0).getJyyljgmc();
                    String sfysid = sfXshjcVos.get(0).getSfysid();
                    String sfys = sfXshjcVos.get(0).getSfys();
                    String gljg = sfXshjcVos.get(0).getGljg();
                    String gljgmc = sfXshjcVos.get(0).getGljgmc();
                    sfXshxqjcVo.setJyyljgmc(jyyljgmc).setSffsName(sffsName).setSfys(sfys).setGljgmc(gljgmc);
                    sfXshxqjcVo.setDaId(daId).setJyrq(jyrq).setSffs(sffs).setJyyljg(jyyljg).setSfysid(sfysid).setGljg(gljg);
                }
            }
            sfXshxqjcVo.setXshjcVoList(sfXshjcVos);
            return sfXshxqjcVo;
        } catch (Exception e) {
            log.error("通过档案ID查询最新随访_血生化检查VO错误: {}, error: {}", e);
            return null;
        }
    }

    /**
     * 依据校验使时间分组查询血生化检查项目
     *
     * @return
     */
    public PageData<SfXshjcByDateVo> fetchBloodBiochemistryIndicatorsByDaIdAndDateCategory(String daid, long page, long pageSize) {
        //构建空的分页对象
        PageData<SfXshjcByDateVo> pageData = new PageData<>();
        pageData.setData(new ArrayList<>());
        pageData.setTotal(0);
        pageData.setCurrPage(page);
        pageData.setPageSize(pageSize);
        pageData.setPages(0);
        // 构建 SfXshjcByDateVo 对象的列表
        List<SfXshjcByDateVo> results = new ArrayList<>();
        //查询该档案下所有的血生化检查数据
        List<SfXshjcVo> sfXshjcVos = sfXshjcMapper.findByDaid(daid);
        if (CollectionUtil.isEmpty(sfXshjcVos)) {
            return pageData;
        }
        // 定义日期格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<String, List<SfXshjcVo>> groupBydateMap = new HashMap<>();
        // 将血生化检查数据按照 JYRQ 进行分组
        for (SfXshjcVo sfXshjcVo : sfXshjcVos) {
            String jyrq = sfXshjcVo.getJyrq().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
            groupBydateMap.computeIfAbsent(jyrq, k -> new ArrayList<>()).add(sfXshjcVo);
        }

        for (Map.Entry<String, List<SfXshjcVo>> entry : groupBydateMap.entrySet()) {
            SfXshjcByDateVo sfXshjcByDateVo = new SfXshjcByDateVo()
                    .setDate(entry.getKey())
                    .setSfXshjcVoList(entry.getValue());
            results.add(sfXshjcByDateVo);
        }

        long total = results.size();
        long fromIndex = (page - 1) * pageSize;
        long toIndex = Math.min(fromIndex + pageSize, total);
        if (fromIndex > total) {
            return pageData;
        }

        List<SfXshjcByDateVo> pageRecords = results.subList((int) fromIndex, (int) toIndex);
        pageData.setData(pageRecords);
        pageData.setTotal(total);
        pageData.setCurrPage(page);
        pageData.setPageSize(pageSize);
        pageData.setPages((total + pageSize - 1) / pageSize);
        return pageData;

    }

    /**
     * 依据项目分组查询血生化检查项目
     *
     * @return
     */
    public PageData<SfXshjcByXmDto> getXshDataByXmAndDaid(String daid, long page, long pageSize) {
        //创建空的分页对象
        PageData<SfXshjcByXmDto> pageData = new PageData<>();
        pageData.setTotal(0);
        pageData.setCurrPage(page);
        pageData.setPageSize(pageSize);
        pageData.setPages(0);

        //根据项目进行数据的分类
        List<SfXshjcByXmDto> results = new ArrayList<>();
        //查询该档案下所有的血生化检查数据
        List<SfXshjcVo> sfXshjcVos = sfXshjcMapper.findByDaidSortByJyrq(daid);
        if (CollectionUtil.isEmpty(sfXshjcVos)) {
            return pageData;
        }

        //依据检验项目进行数据分组
        Map<String, List<SfXshjcVo>> groupedByJyxm = sfXshjcVos.stream()
                .collect(Collectors.groupingBy(SfXshjcVo::getJyxm));

        // 以检验项目的数据类型转换为 List<SfXshjcByXmDto>
        List<SfXshjcByXmDto> sfXshjcByXmDtos = groupedByJyxm.entrySet().stream()
                .map(entry -> new SfXshjcByXmDto()
                        .setJyxm(entry.getKey())
                        .setJyxmName(entry.getValue().get(0).getJyxmName())
                        .setSfXshjcVoList(entry.getValue()))
                .collect(Collectors.toList());

        //计算分页
        long total = sfXshjcByXmDtos.size();
        long fromIndex = (page - 1) * pageSize;
        long toIndex = Math.min(fromIndex + pageSize, total);
        if (fromIndex > total) {
            return pageData;
        }

        //获取分页后的数据
        List<SfXshjcByXmDto> pageRecords = sfXshjcByXmDtos.subList((int) fromIndex, (int) toIndex);
        pageData.setData(pageRecords);
        pageData.setTotal(total);
        pageData.setCurrPage(page);
        pageData.setPageSize(pageSize);
        pageData.setPages((total + pageSize - 1) / pageSize);
        return pageData;
    }

    /**
     * 依据档案id新增血生化检查项目
     *
     * @return
     */
    public String add(String daid) {
        return null;
    }

    public SfXshjcVo getXshByDaId(String daId) {

        return null;
    }

//    /**
//     * 随访一览-血生化检查查询
//     */
//    public PageData<SfXshjcYlvo> getSfXshDataByCriteriasByDaId(SfXshjcSearchVo vo) {
//        List<SfXshjcYlvo> fXshjcYlvos = new ArrayList<>();
//        // 获取当前用户和医院信息
//        Organ organ = UserUtils.getOrgan();
//        // 设置医疗机构
//        vo.setJcyljg(organ.getCode());
//        // 创建分页对象
//        //Page<SfXshjcDto> page = new Page<>(vo.getCurrPage(), vo.getPageSize());
//        // 初始化空的分页结果
//        PageData<SfXshjcYlvo> pageData = new PageData<>();
//        pageData.setTotal(0);
//        pageData.setData(new ArrayList<>());
//        pageData.setCurrPage(vo.getCurrPage());
//        pageData.setPageSize(vo.getPageSize());
//        pageData.setPages(0);
//        // 执行查询
//        List<SfXshjcDto> results = sfXshjcMapper.searchSfXshjc( vo.getKeyword(), vo.getJcyljg(),
//                vo.getBeginDate(), vo.getEndDate(), vo.getJyxmCodes(), vo.getTscode(), vo.getYfcode());
//        // 如果查询结果为空，直接返回空分页数据
//        if (results == null || results.isEmpty()) {
//            return pageData;
//        }
//        // 字典值转换
//        for (SfXshjcDto item : results) {
//
//            // 根据身份证号设置出生日期和年龄
//            try {
//                item.setCsrqAndNlFromIdCard();
//            } catch (ParseException e) {
//                e.printStackTrace();
//
//            }
//        }
//        // 分页处理
//        long total = results.size();
//        long pageSize = vo.getPageSize();
//        long currPage = vo.getCurrPage();
//        long fromIndex = (currPage - 1) * pageSize;
//        long toIndex = Math.min(fromIndex + pageSize, total);
//        List<SfXshjcDto> pageRecords = results.subList((int) fromIndex, (int) toIndex);
//
//        // 封装分页结果
//        pageData.setTotal(total);
//        pageData.setData(pageRecords);
//        pageData.setCurrPage(currPage);
//        pageData.setPageSize(pageSize);
//        pageData.setPages((total + pageSize - 1) / pageSize);
//        return pageData;
//    }

    public PageData<SfXshjcYlvo> getSfXshDataByCriteriasByDaId(SfXshjcSearchVo vo) {
        List<SfXshjcYlvo> fXshjcYlvos = new ArrayList<>();
//        // 获取当前用户和医院信息
//        Organ organ = UserUtils.getOrgan();
//        // 设置医疗机构
//        vo.setJcyljg(organ.getCode());
        // 创建分页对象
        //Page<SfXshjcDto> page = new Page<>(vo.getCurrPage(), vo.getPageSize());
        // 初始化空的分页结果
        PageData<SfXshjcYlvo> pageData = new PageData<>();
        pageData.setTotal(0);
        pageData.setData(new ArrayList<>());
        pageData.setCurrPage(vo.getCurrPage());
        pageData.setPageSize(vo.getPageSize());
        pageData.setPages(0);
        // 执行查询
        List<SfXshjcDto> results = sfXshjcMapper.searchSfXshjc(vo.getKeyword(), vo.getJcyljg(),
                vo.getBeginDate(), vo.getEndDate(), vo.getJyxmCodes(), vo.getTscode(), vo.getYfcode());
        // 如果查询结果为空，直接返回空分页数据
        if (results == null || results.isEmpty()) {
            return pageData;
        }
        // 字典值转换
        for (SfXshjcDto item : results) {
            SfXshjcYlvo sfXshjcYlvo = new SfXshjcYlvo();

            // 根据身份证号设置出生日期和年龄
            try {
                item.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                e.printStackTrace();

            }

            fXshjcYlvos.add(sfXshjcYlvo);

        }
        // 分页处理
        long total = results.size();
        long pageSize = vo.getPageSize();
        long currPage = vo.getCurrPage();
        long fromIndex = (currPage - 1) * pageSize;
        long toIndex = Math.min(fromIndex + pageSize, total);
        List<SfXshjcDto> pageRecords = results.subList((int) fromIndex, (int) toIndex);

        // 封装分页结果
        pageData.setTotal(total);
        pageData.setData(fXshjcYlvos);
        pageData.setCurrPage(currPage);
        pageData.setPageSize(pageSize);
        pageData.setPages((total + pageSize - 1) / pageSize);
        return pageData;
    }

    /**
     * 随访一览-血生化登记检查查询
     */
    public List<SfXshjcVo> getXshDataByDaId(String daid) {
        // 调用 Mapper 查询方法
        List<SfXshjcVo> resultList = sfXshjcMapper.findLatestByDaId(daid);
        // 直接返回结果列表
        return resultList;
    }

    /**
     * 评估结果-新建血生化检查查询
     */
    public PageData<PgjgXshVo> getPgXshDataByCriterias(PgjgXshSearchVo vo) {
        //初始化查询条件
        vo.initDefaults();
        IPage<PgjgXshVo> results = sfXshjcMapper.findPgXshXjDataByCriterias(vo);
        return PageData.of(results);
    }
    /**
     * 当传入的时间参数为空的时候获取系统当前及往一年前的时间参数
     */

    private void setDefaultDateRange(PgjgXshSearchVo searchCriteria) {
        // 获取当前日期
        LocalDate today = LocalDate.now();

        // 获取一年前的日期
        LocalDate oneYearAgo = today.minusYears(1);

        // 将 LocalDate 格式化为 Date 并设置到 searchCriteria
        searchCriteria.setBeginDate(Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        searchCriteria.setEndDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 评估结果-详情血生化检查查询
     *//*
    public PageData<PgjgXshVo> getPgXshXqDataByCriterias(PgjgXshSearchVo vo) {
        Date pgrq = vo.getPgrq();
        String daId = vo.getDaId();
        PgSearchVo pgSearchVo = new PgSearchVo();
        pgSearchVo.setPgrq(pgrq);
        pgSearchVo.setDaId(daId);
        if (vo.getDaId() == null || vo.getDaId().isEmpty()) {
            throw new IllegalArgumentException("档案ID不能为空");
        }
        PgJgDataVo pgJgDataVo = pgJgService.getPgjgDataByDaIdandPgrq(pgSearchVo);
        PgJgVo pgJgVo = pgJgDataVo.getPgJgVo();
        List<PgJgXqVo> pgJgXqVos = pgJgDataVo.getPgJgXqVo();

        Date xshjcks = null;
        Date xshjcjs = null;

        IPage<PgjgXshVo> results = null;

        // 获取档案id血生化检验项目的存储时间范围
        if (pgJgVo != null) {
            //存储的开始时间
            xshjcks = pgJgVo.getXshjcks();
            //储存的结束时间
            xshjcjs = pgJgVo.getXshjcjs();
        }
        // 获取档案id下存储的分页信息
        if (pgJgXqVos != null) {
            for (PgJgXqVo pgJgXqVo : pgJgXqVos) {
                //检验项目
                String jyxm = pgJgXqVo.getJyxm();
                //显示数
                long xss = pgJgXqVo.getXss();
                //开始记录数
                long ksjls = pgJgXqVo.getKsjls();
                //根据评估结果表和评估结果详情表获取时间和分页信息对入参赋值
                vo.setPageSize(xss);
                vo.setCurrPage(ksjls);
                vo.setBeginDate(xshjcks);
                vo.setEndDate(xshjcjs);
                results = sfXshjcMapper.findPgXshXqDataByCriterias(vo);
            }
        }
        return PageData.of(results);
    }
 */
    /**
     * 随访一览-血生化检查查询
     */
    public PageData<SfXshjcYlvo> searchPagedResults(SfXshjcSearchVo searchVo) {
        List<SfXshjcYlvo> results = new ArrayList<>();
        PageData pageData = new PageData();
        pageData.setCurrPage(searchVo.getCurrPage());
        pageData.setPageSize(searchVo.getPageSize());
        IPage<SfXshjcDto> page;
        if (searchVo.getJcyljg().isEmpty()) {
            searchVo.setJcyljg(UserUtils.getOrgan().getCode());
        }
        page = sfXshjcMapper.searchResults(searchVo);
        // 设置总记录数
        pageData.setTotal(page.getTotal());
        pageData.setPages(page.getPages());
        if (page.getRecords() != null && !page.getRecords().isEmpty()) {
            SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
            for (SfXshjcDto record : page.getRecords()) {
                // 创建一次对象并在循环中复用
                CaVo caVo = new CaVo();
                CtVo ctVo = new CtVo();
                PVo pVo = new PVo();
                OhdVo ohdVo = new OhdVo();
                XnmidVo xnmidVo = new XnmidVo();
                BctsVo bctsVo = new BctsVo();
                PthVo pthVo = new PthVo();
                P1npVo p1npVo = new P1npVo();
                CrVo crVo = new CrVo();
                AltVo altVo = new AltVo();
                AlpVo alpVo = new AlpVo();
                //处理年龄
                try {
                    record.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    log.error("身份证号解析年龄错误: {}, error: {}", record, e.getMessage());
                    continue;
                }
                String jyrq = sdfOut.format(record.getJyrq());
                SfXshjcYlvo sfXshjcYlvo = new SfXshjcYlvo()
                        .setDaId(record.getDaId())
                        .setXm(record.getXm())
                        .setXb(record.getXb())
                        .setNl(record.getNl())
                        .setSfzh(record.getSfzh())
                        .setLxfs(record.getLxfs())
                        .setJyrq(jyrq)
                        .setCaVo(caVo.setJysz(record.getJysz1() != null ? record.getJysz1().toString() : "").setTs(record.getTs1()).setDw(record.getDw1()))
                        .setCtV0(ctVo.setJysz(record.getJysz2() != null ? record.getJysz2().toString() : "").setTs(record.getTs2()).setDw(record.getDw2()))
                        .setPVo(pVo.setJysz(record.getJysz3() != null ? record.getJysz3().toString() : "").setTs(record.getTs3()).setDw(record.getDw3()))
                        .setOhdVo(ohdVo.setJysz(record.getJysz4() != null ? record.getJysz4().toString() : "").setTs(record.getTs4()).setDw(record.getDw4()))
                        .setXnmidVo(xnmidVo.setJysz(record.getJysz5() != null ? record.getJysz5().toString() : "").setTs(record.getTs5()).setDw(record.getDw5()))
                        .setBctsVo(bctsVo.setJysz(record.getJysz6() != null ? record.getJysz6().toString() : "").setTs(record.getTs6()).setDw(record.getDw6()))
                        .setPthVo(pthVo.setJysz(record.getJysz7() != null ? record.getJysz7().toString() : "").setTs(record.getTs7()).setDw(record.getDw7()))
                        .setP1npVo(p1npVo.setJysz(record.getJysz8() != null ? record.getJysz8().toString() : "").setTs(record.getTs8()).setDw(record.getDw8()))
                        .setCrV0(crVo.setJysz(record.getJysz9() != null ? record.getJysz9().toString() : "").setTs(record.getTs9()).setDw(record.getDw9()))
                        .setAltV0(altVo.setJysz(record.getJysz10() != null ? record.getJysz10().toString() : "").setTs(record.getTs10()).setDw(record.getDw10()))
                        .setAlpVo(alpVo.setJysz(record.getJysz11() != null ? record.getJysz11().toString() : "").setTs(record.getTs11()).setDw(record.getDw11()));
                results.add(sfXshjcYlvo);

            }
        }
        pageData.setData(results);
        return pageData;


    }

    /**
     * 通过档案ID和检查日期查询随访_血生化检查VO
     */
    public SfXshxqjcVo fetchBiochemicalCheckDataBydaIdandCjrq(XshYlSearchVo vo) {
        SfXshxqjcVo sfXshxqjcVo = new SfXshxqjcVo();
        try {
            List<SfXshjcVo> sfXshjcVos= this.baseMapper.fetchBiochemicalCheckDataBydaIdandCjrq(vo);
            if (sfXshjcVos!=null && !sfXshjcVos.isEmpty()) {
                if (sfXshjcVos.get(0) != null )  {
                    String daId = sfXshjcVos.get(0).getDaId();
                    Date  jyrq = sfXshjcVos.get(0).getJyrq();
                    String jyyljg = sfXshjcVos.get(0).getJyyljg();
                    String  jyyljgmc = sfXshjcVos.get(0).getJyyljgmc();
                    String sffs = sfXshjcVos.get(0).getSffs();
                    String sffsName = sfXshjcVos.get(0).getSffsName();
                    String sfysid = sfXshjcVos.get(0).getSfysid();
                    String sfys = sfXshjcVos.get(0).getSfys();
                    String gljg= sfXshjcVos.get(0).getGljg();
                    String gljgmc = sfXshjcVos.get(0).getGljgmc();
                    sfXshxqjcVo.setDaId(daId);
                    sfXshxqjcVo.setJyrq(jyrq);
                    sfXshxqjcVo.setJyyljg(jyyljg);
                    sfXshxqjcVo.setJyyljgmc(jyyljgmc);
                    sfXshxqjcVo.setSffs(sffs);
                    sfXshxqjcVo.setSffsName(sffsName);
                    sfXshxqjcVo .setSfysid(sfysid);
                    sfXshxqjcVo .setSfys(sfys);
                    sfXshxqjcVo .setGljg(gljg);
                    sfXshxqjcVo .setGljgmc(gljgmc);
                }
            }
            sfXshxqjcVo.setXshjcVoList(sfXshjcVos);
            return sfXshxqjcVo;
        } catch (Exception e) {
            log.error("通过档案ID和检查日期查询随访_血生化检查VO错误: {}, error: {}", vo, e);
            return null;
        }
    }
}
