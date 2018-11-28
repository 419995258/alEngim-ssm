package org.zyyd.base.servlet;

import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2 {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */


    /* * 创建该API的基本信息（这些基本信息会展现在文档页面中）
            * 访问地址：http://项目实际地址/swagger-ui.html
            * 访问地址：http://项目实际地址/doc.html(美化)
            * @return
            */


    //@ApiIgnore  //  写在某个controller上，忽略扫描该类，api

    @Bean
    public Docket encryprionDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("base后台接口")
                .description("后台管理的接口")
                .version("V1.0")
                .contact("pb")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("base后台接口")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zyyd.base.controller"))
                //设置此组只匹配encryption开头的（如请求地址为/encryption/desc）
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }

    @Bean
    public Docket testDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("测试数据相关接口")
                .version("V1.0")
                .description("主要是测试数据的相关接口")
                .contact("pb")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试相关的接口")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zyyd.base.controller"))
                .paths(PathSelectors.ant("/testC/*"))
                .build()
                .apiInfo(apiInfo);
    }


    /**
     *   api开放接口
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 18:24
     */
    @Bean
    public Docket apiDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("engin开放接口")
                .version("V1.0")
                .description("主要是engin开放接口")
                .contact("pb")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("engin开放接口")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zyyd.engin.controller"))
                .build()
                .apiInfo(apiInfo);
    }



    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zyyd.base.controller"))
                //.paths(PathSelectors.any())   //扫描所有接口
                .paths(PathSelectors.ant("/testC/*")) // 只扫描testC接口
                .build();
    }

    *//**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * 访问地址：http://项目实际地址/doc.html(美化)
     * @return
     *//*
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("APIs")
                .description("所有controller的接口")
                .termsOfServiceUrl("")
                .contact("pb")
                .version("1.0")
                .build();
    }*/


}
