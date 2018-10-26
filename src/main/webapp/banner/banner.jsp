<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function () {
        $('#dg').edatagrid({
            url: '${pageContext.request.contextPath }/getAllBanners',
            columns: [[
                /* {field: 'id', title: '编号', hidden:true},*/
                {field: 'name', title: '标题', width: 100},
                {field: 'description', title: '描述', width: 100},
                {
                    field: 'status', title: '状态 0展示 1不展示', width: 100, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {
                    field: 'createDate', title: '日期', width: 100, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {field: 'url', title: '路径', width: 100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 3,
            pageList: [3, 6, 9],
            toolbar: [{
                iconCls: 'icon-add',
                text: "添加",
                handler: function () {
                    $('#dd').dialog("open")
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: "修改",
                handler: function () {
                    var row = $("#dg").edatagrid("getSelected");
                    if (row == null) {
                        alert("请先选中行")
                    } else {
                        var index = $("#dg").edatagrid("getRowIndex", row);
                        $("#dg").edatagrid("editRow", index);
                    }
                }
            }, '-', {
                iconCls: 'icon-remove',
                text: "删除",
                handler: function () {
                    // 销毁所选择的行
                    $('#dg').edatagrid('destroyRow');
                }
            }, '-', {
                iconCls: 'icon-save',
                text: "保存",
                handler: function () {
                    $("#dg").edatagrid("saveRow");
                }
            }],
            updateUrl: "${pageContext.request.contextPath}/updateBanner",
            destroyUrl:"${pageContext.request.contextPath}/deleteteBanner",
            destroyMsg: {
                norecord: {    // 在没有记录选择的时候执行
                    title: 'Warning',
                    msg: '选中要删除的行'
                },
                confirm: {       // 在选择一行的时候执行		title:'Confirm',
                    msg: '确定要删除吗?'
                }
            },



            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath }/' + rowData.url + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>date: ' + rowData.createDate + '</p>' +
                    '<p>description: ' + rowData.description + '</p>' +
                    '<p>path: ' + rowData.url + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

        $('#dd').dialog({
            title: '添加窗口',
            width: 300,
            height: 175,
            closed: true,
            buttons: "#reg"
        });

        /*添加表单验证*/
        $("#name").validatebox({
            required: true,
            missingMessage: "图片名称不能为空",
        });
        $("#description").validatebox({
            required: true,
            missingMessage: "图片描述不能为空",
        });
        $("#filename").validatebox({
            required: true,
            missingMessage: "图片文件不能为空",
        });

        $('#btn').linkbutton({});
    })

    /*添加轮播图*/
    function addBanner() {
        $('#dd').form("submit", {
            url: "${pageContext.request.contextPath }/addBanner",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                //alert(data)
                if (data) {
                    $('#dd').dialog("close");
                    $('#dg').datagrid("load");
                    $.messager.show({
                        title: '我的消息',
                        msg: '添加成功',
                        timeout: 3000,
                        showType: 'slide'
                    });

                } else {
                    $.messager.show({
                        title: '我的消息',
                        msg: '添加失败',
                        timeout: 3000,
                        showType: 'slide'
                    });
                }
            }
        });
    }

</script>


<table id="dg"></table>
<form method="post" id="dd" enctype="multipart/form-data">
    图片名称：<input id="name" name="name"/><br/>
    图片描述：<input id="description" name="description"/><br/>
    图片状态：<input type="radio" name="status" value="0"/>展示<input type="radio" name="status" value="1"/>不展示<br/>
    上传图片：<input type="file" name="fileName" id="filename"/>
</form>
<div id="reg" style="text-align: center">
    <a href="javascript:void(0)" id="btn" onclick="addBanner()">添加</a>
</div>
