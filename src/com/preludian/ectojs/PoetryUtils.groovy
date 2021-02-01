package com.preludian.ectojs


@Grab('org.tomlj:tomlj:1.0.0')

import org.tomlj.*
import java.nio.file.Paths
import java.nio.file.Path


def getInfo(Map conf = [:]) {

    project_toml = conf['file'] ?: "${env.WORKSPACE}/pyproject.toml"

    Path source = Paths.get(project_toml);
    TomlParseResult result = Toml.parse(source);

    return [
            version: result.getString("tool.poetry.version")
    ]
}

return this