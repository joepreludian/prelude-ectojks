package com.preludian.ectojs


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