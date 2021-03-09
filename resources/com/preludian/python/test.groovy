
def loadFilesIntoWorkspace(List files = []) {

    files.each { source ->

        def destination = item.split('/').last()
        def fileContent = libraryResource source

        writeFile file: destination, text: fileContent
        print("-> Wrote ${destination} << ${source}")
    }
    
}


loadFilesIntoWorkspace(['com/preludian/python/poetry.py', 'com/preludian/python/pyproject.toml'])
