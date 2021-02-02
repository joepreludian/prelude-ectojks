package com.preludian.ectojks

/**
 * Load files into workspace: Given package, It will execute a Jenkinspipeline command that brings files from
 * a shared library resources and adds to the workspace with the same name;
 *
 * @param conf [
 *      package: (String) Where the resource is located into your shared library
 *      files: List<String> The name of the files that will be processed
 * ]
 *
 * @return void
 */
def loadFilesIntoWorkspace(Map conf = [package: '', files: []]) {
    srcPackage = conf['package'] ?: ''
    files = conf['files'] ?: []

    files.each { filename ->

        def fileContent = libraryResource "${srcPackage}/${filename}"

        writeFile file: filename, text: fileContent
        print("-> Wrote ${filename} into current Workspace")

    }
}

/**
 *
 * @param conf [
 *      header: (String) It will create a h4 with this header;
 *      displayName: (String) Contains a brief text; It will attach the build no. e.g (34 - <yourText>)
 *      setRootBuild: (bool - default false) Set this to true if you want that your pipeline changes the
 *                    Root build Description
 *      cols: List<String> - Configure the header of your table with useful information
 *      rows: List<List<String>> - Add a List with Lists representing each row;
 *            e.g.: [['row1_col1', 'row1_col2'], ['row2_col1', 'row2_col2'] ...]
 * ]
 * @return void
 */
def buildFancyDescription(Map conf = [
        header: null,
        displayName: null,
        setRootBuild: false,
        cols: [],
        rows: []
]) {

    System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []
    displayName = conf['displayName'] ?: null
    setRootBuild = conf['setRootBuild'] ?: false

    table_cols = new StringBuffer()
    cols.each { item ->
        table_cols.append("<th>${item}</th>")
    }

    table_rows = new StringBuffer()
    rows.each { line ->
        table_rows << '<tr>'
        line.each { col ->
            table_rows << "<td>${col}</td>"
        }
        table_rows << '</tr>'
    }

    htmlContent = """
        <table class=\"sortable pane bigtable stripped-odd\">
            <tr class=\"header\">${table_cols}</tr>
            ${table_rows}
        </table>
        <h4>${header}</h4>
    """

    if (setRootBuild == true) {
        currentBuild.rawBuild.project.description = htmlContent
    }

    if (displayName != null) {
        currentBuild.displayName = "${currentBuild.number} - ${conf['displayName']}"
    }

    currentBuild.description = htmlContent
}

return this