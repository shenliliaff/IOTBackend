package com.up.iotbackend.generator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

import java.util.Collections;

/**
 * @author Feng.An
 * @since 2022-12-11
 * @date 2022年12月11日 20:40
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/demo_java_api?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&nullCatalogMeansCurrent=true",
                        "root",
                        "Vitesco2022!!!!")
                .globalConfig(builder -> {
                    builder.author("Feng.An") // 设置作者/ 覆盖已生成文件
                            .enableSwagger() // 开启 swagger 模式
                            // 指定输出目录
                            //直接右键复制项目根目录的绝对路径
                            //.outputDir("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/java")
                            .outputDir("src/main/java")
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                            //.fileOverride();
                })
                .packageConfig(builder -> {
                    builder.parent("com.up.iotbackend") // 设置父包名
                            // 设置mapperXml生成路径
                            //直接右键复制项目mapper文件夹的绝对路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("up_user","up_group","up_role","up_permission","up_user_group_ref","up_user_role_ref",
                            "up_role_permission_ref","up_group_role_ref","up_device_info","up_device_log_info","up_device_key_info",
                            "up_location_info","up_venue_info","up_device_type_info","up_device_admin_ref");
                })
//                .templateConfig(builder -> {
//                    builder.disable(TemplateType.ENTITY)
//                            .entity("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/entity.java")
//                            .service("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/service.java")
//                            .serviceImpl("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/serviceImpl.java")
//                            .mapper("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/mapper.java")
//                            .xml("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/mapper.xml")
//                            .controller("/Users/anfeng/up/02-code/03-api/IOTBackend/src/main/resources/templates/controller.java");
//                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板(需要导包)，默认的是Velocity引擎模板
                .execute();
    }

}



