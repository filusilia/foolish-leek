# foolish-leek
聪明的小韭菜

项目支持
----

1. 服务端
- 嘿你猜怎么着，现在能登录了，还能查询了
2. 移动端[点这里试试看](https://github.com/filusilia/foolish_leek_flutter)
3. web端(在做了在做了)

## 功能列表

1. 登录(在做了在做了)
- 简单权限，可以登录了
2. 权限(在做了在做了)
3. 查询基金(在做了在做了)
- 可以分页查询
- 可以分页搜索,支持基金号,拼音,基金名称智能区分搜索
4. 保存收藏(在做了在做了)
- 可以收藏(目前暂无效果)
5. 推送(在做了在做了)
6. ?

## tips

1. -Dfile.encoding=UTF-8
2. springboot2.4.0起使用跨域设置时使用config.addAllowedOriginPattern("*").
3. springboot2.6使用springfox时会报错"Failed to start bean
   'documentationPluginsBootstrapper'; nested exception is
   java.lang.NullPointerException",原因是启用了全新的URL匹配'PathPatternParser',会与swagger产生兼容性问题,目前的解决方案是使用旧有的'AntPathMatcher',
   配置如下:'spring.mvc.pathmatch.matching-strategy=ant_path_matcher'.
   可以参考[这里](https://github.com/springfox/springfox/issues/3462).对于新建bean的解决方案,目前会与knife4j产生兼容问题,会不生成文档.

## 感谢 [韭菜盒子](https://github.com/LeekHub/leek-fund)
