<html>
<title>
    Fisiere-laborator5
</title>
<body>
    Numele catalogului: ${name}
    <br><br>
    Elementele catalogului sunt:
    <br>
        <#list documents as document>
            Numele documentului: ${document.name} si autorul sau ${document.author}
            <br>
        </#list>
</body>
</html>