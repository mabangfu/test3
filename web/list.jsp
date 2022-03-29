<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>联系人页面</title>
    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet"/>
</head>
<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<style type="text/css">
    td, th {
        text-align: center;
    }
</style>

<script type="text/javascript">
    var id;
    $(function () {
        //    添加 修改的模态框
        $('#myLargeModalLabel').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var code = button.data('code');
            var modal = $(this);
            if (code == 0) {
                //   添加联系人
                modal.find('.modal-title').text("添加联系人");
            } else {
                modal.find('.modal-title').text("修改联系人");
                modal.find('.modal-body input').val(user);
            }
        });
        //    删除的模态框
        $('#deleteModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            id = button.data('id');
        });
    });

    //    删除用户
    function saveDelete() {
        window.location = "${path}/deleteUser?id=" + id;
    }

    //    修改用户
    function updateUser(id) {

        window.location = "${path}/findOne?id=" + id;
    }

    //多选或反选
    function selectAll() {
        var check = document.getElementsByName("ids");
        for (var i = 1; i <= check.length; i += 1) {
            if (check[i - 1].checked) {
                check[i - 1].checked = false;
            } else {
                check[i - 1].checked = true;
            }
        }
    }

    //    批量删除
    function deleteAll() {
        /*var b = confirm("你确定要删除这些记录吗？");
        var url = "/usermanager/deleteAll?";
        var check = document.getElementsByName("ids");
        var ids="";
        for (var i = 0; i < check.length; i++) {
            if (check[i].checked) {
                ids = ids + "ids=" + check[i].value + "&";
            }
        }
        if (b) {
            if (id != "/usermanager/deleteAll?") {
                window.location = url + ids;
            }
        }*/
        if (confirm("你确定要删除这些记录吗？")) {
            let flag = false;
            let listOf = document.getElementsByName("ids");
            for (let i = 0; i < listOf.length; i++) {
                if (listOf[i].checked) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                document.getElementById("deleteForm").submit();
            }
        }
    }

    // $(function(){
    //     $("#update").click(function(){
    //         let username = $("tr:eq(1)").text();
    //         console.log(username);
    //     })
    // })


</script>


<body>
<div class="container">
    <form>
        <div class="form-inline" style="float: left">
            <label for="username">姓名</label>
            <input type="text" class="form-control" value="${condition.username[0]}" name="username" id="name"
                   placeholder="请输入姓名">
            <label for="address">籍贯</label>
            <input type="text" class="form-control" value="${condition.address[0]}" name="address" id="address"
                   placeholder="请输入籍贯">
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-search">查询</span>
            </button>
        </div>
        <div style="float: right;">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg"
                    data-code="0">添加联系人
            </button>
            <button type="button" class="btn btn-primary" onclick="deleteAll()">删除选中
            </button>
        </div>
    </form>
</div>
<!-- 以下为联系人页面表格 -->
<div class="container" style="margin-top: 25px;">
    <form action="/usermanager/deleteAll" method="post" id="deleteForm">
        <table border="1" class="table table-bordered table-hover">
            <tr bgcolor="#C8E5BC" style="font-size: 20px;">
                <th><input type="checkbox" onclick="selectAll()" id="all"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${page.list}" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="ids" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.username}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                            <%--                        <button type="button" value="update" id="update" class="btn btn-primary"--%>
                            <%--                                onclick="updateUser(${user.id})">修改--%>
                            <%--                        </button>--%>

                        <button type="button" value="update" id="update" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg"
                               >修改
                        </button>

                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#deleteModal" data-id="${user.id}">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <%--    分页--%>
    <tr>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page.current == 1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${page.current != 1}">
                <li>
                    </c:if>
                    <a href="${path}/findAllUserByPage?username=${condition.username[0]}&address=${condition.address[0]}&current=${page.current - 1}&size=10"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${page.pageNum}" step="1" varStatus="s">
                    <c:if test="${page.current == s.count}">
                        <li class="active">
                    </c:if>
                    <c:if test="${page.current != s.count}">
                        <li>
                    </c:if>
                    <a href="${path}/findAllUserByPage?username=${condition.username[0]}&address=${condition.address[0]}&current=${s.count}&size=10">${s.count}</a>
                    </li>
                </c:forEach>
                <c:if test="${page.current == page.pageNum}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${page.current < page.pageNum}">
                <li></c:if>
                    <a href="${path}/findAllUserByPage?username=${condition.username[0]}&address=${condition.address[0]}&current=${page.current + 1}&size=10"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span class="lead" style="line-height: 35px;">&ensp;共${page.total}条记录，共${page.pageNum}页</span>
            </ul>
        </nav>
    </tr>
</div>

<%--修改、添加联系人模态框--%>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myLargeModalLabel" style="text-align: center;font-size: 20px">添加联系人</h4>
            </div>
            <form action="${path}/addUsers" method="post" style="margin-left: 20px">
                <div class="form-group">
                    <label for="username">姓名：</label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="请输入姓名">
                </div>
                <div>
                    <label for="sex">性别：</label>
                    <input type="radio" name="gender" checked="checked" value="1">男
                    <input type="radio" name="gender" value="0">女
                </div>

                <div class="form-group">
                    <label for="age">年龄：</label>
                    <input type="text" name="age" class="form-control" id="age" placeholder="请输入年龄">
                </div>
                <div>
                    <label for="address">籍贯：</label>
                    <select class="form-control" name="address">
                        <option selected="selected">魏国</option>
                        <option>蜀国</option>
                        <option>吴国</option>
                    </select>
                </div>
                <div>
                    <label for="qq">QQ：</label>
                    <input type="text" name="qq" class="form-control" id="qq" placeholder="请输入QQ号码"/>
                </div>
                <div class="form-group">
                    <label for="email1">Email：</label>
                    <input type="email" name="email" class="form-control" id="email1" placeholder="请输入邮箱地址">
                </div>
                <div style="text-align: center;">
                    <input type="submit" value="保存"/>
                    <input type="reset" value="重置"/>
                    <input type="button" data-dismiss="modal" value="取消"/>
                </div>
            </form>
        </div>
    </div>
</div>

<%--删除模态框--%>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">删除提示</h4>
            </div>
            <div class="modal-body">
                你确定要删除此记录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveDelete()">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
