String[] configFiles = {
    (StrUtil.isNotBlank(environment) ? "application-" + environment : "application") + ".properties",
    "application.yml",
    (StrUtil.isNotBlank(environment) ? "application-" + environment : "application") + ".yml"
};

// 创建一个 Map 来保存最终的配置
Map<String, Object> finalConfig = new HashMap<>();

for (String configFile : configFiles) {
    ClassPathResource resource = new ClassPathResource(configFile);
    if (resource.getUrl() == null) continue;

    if (configFile.endsWith(".properties")) {
        Props props = new Props(resource.getUrl());
        Map<String, Object> propMap = new HashMap<>();
        props.forEach((k, v) -> propMap.put(k.toString(), v));
        merge(finalConfig, propMap);
    } else if (configFile.endsWith(".yml") || configFile.endsWith(".yaml")) {
        Map<String, Object> yamlMap = YamlUtil.load(new InputStreamReader(resource.getStream()));
        merge(finalConfig, yamlMap);
    }