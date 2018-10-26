<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function () {
        $('#album').treegrid({
            url: '${pageContext.request.contextPath }/getAllAlbum',
            idField: 'id',
            treeField: 'name',
            columns: [[
                {title: '名字', field: 'name', width: 180},
                {field: 'url', title: '下载路径', width: 60, align: 'right'},
                {field: 'size', title: '大小', width: 80},
                {field: 'duration', title: '时长', width: 80}
            ]],
            onDblClickRow:function (rowIndex) {
                if(!rowIndex.url==""){
                    $("#mp3").dialog("open");
                    $("#yp").prop("src","${pageContext.request.contextPath }/"+rowIndex.url);
                }
            },
            method: "get",
            fit: true,
            fitColumns: true,

            toolbar: [{
                iconCls: 'icon-tip',
                text: "专辑详情",
                handler: function () {
                    var row = $("#album").treegrid("getSelected");
                    if (row==null || row.brief==null) {
                        alert("请先选中专辑");
                    } else {
                        $("#detailAlbum").dialog("open");
                        $("#imgAlbum").prop("src","${pageContext.request.contextPath }/"+row.coverImg);
                        $("#albumName").html(row.name);
                        $("#authorName").html(row.author);
                        $("#broadCastName").html(row.broadCast);
                        $("#countName").html(row.count);
                        $("#brirfName").html("&nbsp;&nbsp"+row.brief);
                        $("#publishDatafName").html("发布日期："+row.publishData);
                    }

                }
            }, '-', {
                iconCls: 'icon-add',
                text: "添加专辑",
                handler: function () {
                    $('#formAlbum').dialog("open")
                }
            }, '-', {
                iconCls: 'icon-add',
                text: "添加章节",
                handler: function () {
                    var row = $("#album").edatagrid("getSelected");
                    if (row == null) {
                        alert("请先选中行")
                    } else {
                        $('#formChapter').dialog("open");
                        $("#albumId").val(row.id);
                    }
                }
            }, '-', {
                iconCls: 'icon-undo',
                text: "下载音频",
                handler: function () {
                    var row = $("#album").treegrid("getSelected");
                    if (row==null || row.size==null) {
                        alert("请先选中章节");
                    } else {
                        window.location.href="${pageContext.request.contextPath }/download?fileName="+encodeURIComponent(row.url);
                    }
                }
            }]
        });
        $('#formAlbum').dialog({
            title: '添加专辑',
            width: 300,
            height: 250,
            closed: true,
            buttons: "#regAlbum"
        });
        $('#formChapter').dialog({
            title: '添加专辑',
            width: 300,
            height: 250,
            closed: true,
            buttons: "#regChapter"
        });

        /*专辑详情*/
        $('#detailAlbum').dialog({
            title: '专辑详情',
            width: 400,
            height: 400,
            closed: true
        });
        $('#btn').linkbutton({});

        $('#btn2').linkbutton({});

        /*添加表单验证*/
        $("#name").validatebox({
            required: true,
            missingMessage: "专辑名称不能为空"
        });
        $("#count").validatebox({
            required: true,
            missingMessage: "专辑集数不能为空"
        });
        $("#score").validatebox({
            required: true,
            missingMessage: "专辑评分不能为空"
        });
        $("#author").validatebox({
            required: true,
            missingMessage: "图片名称不能为空"
        });
        $("#broadCast").validatebox({
            required: true,
            missingMessage: "专辑作者不能为空"
        });
        $("#brief").validatebox({
            required: true,
            missingMessage: "专辑播音不能为空"
        });
        $("#publishData").validatebox({
            required: true,
            missingMessage: "发布日期不能为空"
        });
        $("#filename").validatebox({
            required: true,
            missingMessage: "图片文件不能为空"
        });




        $("#nameChapter").validatebox({
            required: true,
            missingMessage: "发布日期不能为空"
        });
        $("#sizeChapter").validatebox({
            required: true,
            missingMessage: "图片文件不能为空"
        });
        $("#durationChapter").validatebox({
            required: true,
            missingMessage: "专辑播音不能为空"
        });
        $("#filename2").validatebox({
            required: true,
            missingMessage: "图片文件不能为空"
        })
    });



    function addAlbum() {
        $('#formAlbum').form("submit", {
            url: "${pageContext.request.contextPath }/addAlbum",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                if (data) {
                    $('#formAlbum').dialog("close");
                    $('#album').treegrid("load");
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
    function addChapter() {
        $('#formChapter').form("submit", {
            url: "${pageContext.request.contextPath }/addChapter",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                if (data) {
                    $('#formChapter').dialog("close");
                    $('#album').treegrid("load");
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


<table id="album"></table>


<form method="post" id="formAlbum" enctype="multipart/form-data">
    专辑名字：<input id="name" name="name"/><br/>
    专辑集数：<input id="count" name="count"/><br/>
    专辑评分：<input id="score" name="score"/><br/>
    专辑作者：<input id="author" name="author"/><br/>
    专辑播音：<input id="broadCast" name="broadCast"/><br/>
    专辑简介：<input id="brief" name="brief"/><br/>
    上传图片：<input type="file" name="fileName" id="filename"/>
</form>
<div id="regAlbum" style="text-align: center">
    <a href="javascript:void(0)" id="btn" onclick="addAlbum()">添加</a>
</div>

<form method="post" id="formChapter" enctype="multipart/form-data">
    章节名字：<input id="nameChapter" name="name"/><br/>
    章节大小：<input id="sizeChapter" name="size"/><br/>
    播放时间：<input id="durationChapter" name="duration"/><br/>
    <input id="albumId" name="albumId" type="hidden" value=""/><br/>
    上传图片：<input type="file" name="fileName" id="filename2"/>
</form>
<div id="regChapter" style="text-align: center">
    <a href="javascript:void(0)" id="btn2" onclick="addChapter()">添加</a>
</div>
<%--播放--%>
<div id="mp3" class="easyui-dialog" title="播放" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <audio id="yp" src="" controls = "controls" preload="auto"></audio>
</div>

<%--专辑详情--%>

<table id="detailAlbum">
    <tr>
        <td rowspan="6" valign="top">
            <img src="" id="imgAlbum" style="width: 250px; height: 300px"/>
        </td>

    </tr>
    <tr>
        <td valign="top" align="left">
            <span id="albumName"></span>
        </td>

    </tr>
    <tr>
        <td valign="top" align="left">
            <span style="color: greenyellow">* * * * *</span>
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
           作者:<span id="authorName"></span>
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
            播音:<span id="broadCastName"></span>
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
            集数:<span id="countName"></span>
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
            内容简介:
        </td>
        <td valign="top" align="center">
            <span id="publishDatafName" style="text-align: right"></span>
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
            <span id="brirfName"></span>
        </td>
    </tr>

</table>
