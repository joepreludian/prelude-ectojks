package com.preludian.ectojs


@Grab('org.tomlj:tomlj:1.0.0')

import org.tomlj.Toml
import java.nio.file.Paths
import java.nio.file.Path


def getInfo() {

    Path source = Paths.get("./pyproject.toml");
    TomlParseResult result = Toml.parse(source);

    return [
            version: result.getString("tool.poetry.version")
    ]
}

return this