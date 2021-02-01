@Grab('org.tomlj:tomlj:1.0.0')

import org.tomlj.*
import java.nio.file.Paths
import java.nio.file.Path


def getInfo() {

    Path source = Paths.get("./pyproject.toml");

    TomlParseResult result = Toml.parse(source);
    //result.errors().forEach(error -> System.err.println(error.toString()));

    String value = result.getString("tool.poetry.version")
    return [
            version: result.getString("tool.poetry.version")
    ]
}

return this