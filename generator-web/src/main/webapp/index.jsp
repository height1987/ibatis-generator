<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/jsoneditor.min.css">
    <script type="text/javascript" src="js/jsoneditor.min.js"></script>
    <script src="js/jquery-1.9.1.js">
    </script>
    <script>
        $(document).ready(function(){
            $("#btn1").click(function(){
                var toUrl = 'parseSql/parse'+
                        '?originSql='+encodeURIComponent($("#originSql").val());
                $.ajax({url:toUrl,async:false,dataType:'json',
                success: succFunction});

            })
            function succFunction(res) {
             var json = eval(res);
             $("#classMetaData").val(JSON.stringify(json.classMetaData,null,4));
             $("#tableMetaData").val(JSON.stringify(json.tableMetaData,null,4));

            }

            $("#btn2").click(function(){
                var toUrl = 'template/generate?classMetaData='+encodeURIComponent(myTrim($("#classMetaData").val()))+
                '&tableMetaData='+encodeURIComponent(myTrim($("#tableMetaData").val()));
                $.ajax({url:toUrl,async:false,dataType:'json',
                success: succFunction2});

            })

            function succFunction2(res) {
             var json = eval(res);


             $("#xml").html(replaceN(json.sql));
             $("#class").html(replaceN(json.entity));
            }

            function myTrim(str)
            {
               return str.replace(/\ +/g,"").replace(/[ ]/g,"").replace(/[\r\n]/g,"");
            }

            function replaceN(str)
            {
               return str.replace(/\n/g,"\r\n");
            }

        })

    </script>
</head>
<body>
<div align="center" vertical-align="middle" style="margin: 20 auto;">
    <table>
        <tr style="margin: 10 auto;">
            <td>
                原始sql
            </td>
            <td>
                <textarea cols="60" rows="20" id="originSql"></textarea>
            </td>


        </tr>
    </table>
    <button id="btn1" type="button">解析字段</button>
    <table>
        <tr style="margin: 10 auto;">
            <td width="50px">
                表属性
            </td>
            <td>
                <textarea cols="60" rows="20" id="tableMetaData"></textarea>
            </td>

            <td width="50px">
                类属性
            </td>
            <td>
                <textarea id="classMetaData" cols="60" rows="20"></textarea>
            </td>
        </tr>
    </table>
    <button id="btn2" type="button">生成模板</button>
    <table>
        <tr style="margin: 10 auto;">
            <td width="50px">
                java类
            </td>
            <td>
                <textarea cols="60" rows="20" id="class"></textarea>
            </td>

            <td width="50px">
                xml
            </td>
            <td>
                <textarea id="xml" cols="60" rows="20"></textarea>
            </td>
        </tr>
    </table>
</div>

</body>

</html>