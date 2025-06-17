package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcYlvo implements Serializable {
    /**
     * 姓名
     */
    private String xm;
    /**
     * 档案编号
     */
    private String daId;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 性别
     */
    @NameField(type = DictType.DICT,key = "XB",target = "xbName")
    private String xb;
    private String xbName;

    /**
     * 年龄
     */
    private Integer nl;
    /**
     * 联系方式
     */
    private String lxfs;
    /**
     * 检验日期 日期格式:yyyy-MM-dd
     */
    private String jyrq;
    /**
     * 总钙测定
     */
    private CaVo caVo;
    /**
     * 降钙素测定
     */
    private CtVo ctV0;
    /**
     * 无机磷测定
     */
    private PVo pVo;
    /**
     * 25羟基维生素D测定
     */
    private OhdVo ohdVo;

    /**
     * 血清骨钙素N端中分子片段
     */
    private XnmidVo xnmidVo;
    /**
     * β－胶原降解产物测定
     */
    private BctsVo bctsVo;

    /**
     * 甲状旁腺激素测定
     */
    private PthVo pthVo;
    /**
     * 总I型胶原氨基端延长测定
     */
    private P1npVo p1npVo;
    /**
     * 肌酐
     */
    private CrVo crV0;

    /**
     * 丙氨酸氨基转移酶测定
     */
    private AltVo altV0;
    /**
     * 碱性磷酸酶测定
     */
    private AlpVo alpVo;
}
