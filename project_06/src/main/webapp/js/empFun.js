/*执行方法渲染*/
layui.use('table',function (){

    var table = layui.table;
    var $=layui.$;
    var layer=layui.layer;
    var form=layui.form;
    var laydate=layui.laydate;
    var currentPage = 1; //创建全局变量，指定当前页是第1页
    var selectJson={};//全局变量 初始化为空；查询后赋值，使其根据查询数据加载页面

    //日期常规用法
    laydate.render({
        elem: '#hiredate',
        type: 'date',
        calendar:true,
        format:'yyyy-MM-dd'//格式可以任意设置
    });

    //下拉列表
    loadDeptInfos()

    //页面信息显示
    loadEmpList();

    /************************* 自定义函数**************************************/
    //员工信息页面加载：用于第一次进入页面 和 查询后显示的页面
    function loadEmpList(){
        table.render({
            elem:'#demo' //表示跟表格容器的id进行绑定
            ,height:400
            ,url:'emp/empInfos'/*数据接口*/
            ,page:true/*开启分页*/
            ,limit:5
            ,limits:[5,10,15,20]
            ,where:selectJson //where表示把值传递到后端
            ,toolbar: '#toolbarDemo'
            ,defaultToolbar: ['filter', 'exports', 'print',
                { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    ,layEvent: 'LAYTABLE_TIPS'
                    ,icon: 'layui-icon-tips'
                }]
            ,cols:[[
                {type:'checkbox',fixed:'left'}
                ,{field: 'empno', title: '编号', width:100, sort: true}
                ,{field: 'ename', title: '姓名', width:100}
                ,{field: 'job', title: '职位', width:100, }
                ,{field: 'mgr', title: '上司', width:90,sort: true}
                ,{field: 'hiredate', title: '入职日期', width: 170,sort: true}
                ,{field: 'sal', title: '薪水', width: 80, sort: true}
                ,{field: 'comm', title: '奖金', width: 80,}
                ,{field: 'dname', title: '部门', width: 100,
                    templet:'<div>{{ d.dept.dname }}</div>'}
                ,{field: 'loc', title: '地址', width: 100,
                    templet: '<div>{{ d.dept.loc }}</div>'}
                ,{field: 'right',width: 200,align:'center',toolbar:'#barDemo'}
            ]]
            /*渲染完毕之后的回调函数*/
            ,done: function(res, curr, count){
                //得到当前页码
                console.log(curr);
                //给currentPage赋值
                currentPage = curr;
            }
        });
    }

    //条件查询
    form.on('submit(btnSelect)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        selectJson=data.field;//赋值，然后调用loadEmpList()方法：
        loadEmpList();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    /*====头工具栏事件：批量删除和添加=====================*/
    table.on('toolbar(test)',function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);//layui固定写法,也可以用<table>标id名“demo”取代
        console.log(checkStatus)//选中行的信息：包括date,isAll等信息
        console.log(checkStatus.data)//选中行的数据信息
        console.log(checkStatus.data.length)//选中行的数据信息的长度
        var arrEmp=checkStatus.data;
        switch(obj.event){
            case 'add'://新增
                layer.open({
                    type:1,//弹出类型
                    title:"添加员工信息",
                    area:["480px","580px"],//弹框尺寸
                    anim:3,//弹出动画效果
                    shade:0.5,//阴影
                    content:$('#saveEmpDiv')
                });
                break;
            case 'delBatch'://删除
                if(arrEmp.length===0){
                    return layer.msg("请选择需要删除的员工信息",{icon: 7,time:2000,anim: 3,shade:0.5})
                }
                layer.confirm("确定批量删除么",function (index){
                    //7905,7900,7844  -> [7905,7900,7844]
                    //springmvc能够接收字符串类型的数字，以,号分隔，springmvc会自动转换了Integer的数组
                    var empNoStr="";
                    for(var i=0;i<arrEmp.length;i++){
                        empNoStr+=arrEmp[i].empno+",";
                    }
                    //去除字符串多出的逗号，返回新的子字符串
                    empNoStr=empNoStr.substring(0,empNoStr.length-1);
                    //向后端服务器发出删除指令
                    delBatchEmpByEmpNo(empNoStr);
                    //关闭窗口
                    layer.close(index);
                })
                break;

        }
    })
    function delBatchEmpByEmpNo(empNoStr){
        $.post(
            "/emp/deleteBatchEmp",
            {"empnos":empNoStr},
            function (jsonObj){
                console.log(jsonObj)
                if(jsonObj === "success"){
                    layer.msg("删除成功",{icon:1,time:2000,anim:4,shade:0.5})
                    //重新渲染当前页的表格数据
                    table.reload('demo',{
                        page: {
                            curr: currentPage //重新从指定的页码开始渲染表格数据
                        }
                    });
                }else{
                    layer.msg("删除失败",{icon:1,time:2000,anim:4,shade:0.5})
                }
            },"text" //text:表示后端响应的是文本,不写的话后端删除数据后在前端页面无法显示删除成功或失败，if判定进不去
        ).error(function (){
            layer.msg("服务器异常",{icon:3,time:2000,anim:6,shade:0.5})
        });
    }

    //添加
    form.on('submit(formAdd)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        var addData=data.field;//赋值，然后调用loadEmpList()方法：
        saveEmp(addData);
        //关闭所有的弹出窗口
        layer.closeAll();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    function saveEmp(addData){
        $.post(
            "/emp/insertEmp",
            addData,
            function (jsonObj){
                console.log(jsonObj)
                if(jsonObj === "success"){
                    layer.msg("添加成功",{icon:1,time:2000,anim:4,shade:0.5})
                    //重新渲染当前页的表格数据
                    loadEmpList();
                }else{
                    layer.msg("添加失败",{icon:1,time:2000,anim:4,shade:0.5})
                }
            },"text" //text:表示后端响应的是文本,不写的话后端删除数据后在前端页面无法显示删除成功或失败，if判定进不去
        ).error(function (){
            layer.msg("服务器异常",{icon:3,time:2000,anim:6,shade:0.5})
        });
    }


    /*====监听行工具事件：单行删除和编辑功能=======*/
    //根据员工编号删除和编辑功能：
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，
        // test 是 table 原始容器的属性 lay-filter="对应的值"

        var thisData = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        console.log(thisData)

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                delEmpByEmpno(obj);
                layer.close(index);
            });
        } else if(layEvent === 'edit'){ //编辑
            //1.先给表单赋值
            form.val("updEmpFormFilter",{//class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "empno":thisData.empno // "name": "value"
                ,"ename":thisData.ename
                ,"job": thisData.job
                ,"mgr": thisData.mgr
                ,"sal": thisData.sal
                ,"hiredate": thisData.hiredate
                ,"comm": thisData.comm
                ,"deptno": thisData.dept.deptno
            });
            //2.弹出编辑页面
            layer.open({
                type:1,//弹出类型
                title:"修改员工信息",
                area:["480px","580px"],//弹框尺寸
                anim:3,//弹出动画效果
                shade:0.5,//阴影
                content:$('#updEmpDiv')
            });
        }
    });
    function delEmpByEmpno(obj) {
        $.post(
            "/emp/deleteEmp",
            {"empno":obj.data.empno},
            function (jsonObj){
                if(jsonObj === "success"){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.msg("删除成功",{icon:1,time:2000,anim:4,shade:0.5})
                }else{
                    layer.msg("删除失败",{icon:1,time:2000,anim:4,shade:0.5})
                }
            },"text"
        ).error(function (){
            layer.msg("服务器异常",{icon:3,time:2000,anim:6,shade:0.5})
        });
    }

    //编辑
    form.on('submit(formUpdate)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        var updateData=data.field;//赋值，然后刷新当前页
        updateEmp(updateData);
        //关闭所有的弹出窗口
        layer.closeAll();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    function updateEmp(updateData){
        $.post(
            "/emp/changeEmp",
            updateData,
            function (jsonObj){
                if(jsonObj==="success"){
                    layer.msg("修改成功",{icon:1,time:2000,anim:4,shade:0.5})
                    //重新渲染当前页的表格数据
                    table.reload('demo',{
                        page: {
                            curr: currentPage //重新从指定的页码开始渲染表格数据
                        }
                    });
                }else{
                    layer.msg("修改失败",{icon:1,time:2000,anim:4,shade:0.5})
                }
            },"text"
        ).error(function (){
            layer.msg("服务器异常",{icon:3,time:2000,anim:6,shade:0.5})
        });
    }

    /***********下拉列表和验证功能*************/
    //加载部门下拉列表
    function loadDeptInfos(){
        $.post(
            "/emp/deptInfos",
            function (deptList) {
                var optionStr = "<option value=''>===请选择===</option>";
                $(deptList).each(function (){
                    optionStr+="<option value='"+this.deptno+"'>"+this.dname+"</option>"
                })
                //把生成好的选项加入到下拉框中
                $("#selDept").append(optionStr);
                $("#saveDept").append(optionStr);
                $("#updDept").append(optionStr);
                form.render('select'); //刷新sel
            },"json"
        ).error(function (){
            layer.msg("请求数据异常！")
        })
    }

    //表单验证：
    form.verify({
        ename: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
            //如果不想自动弹出默认提示框，可以直接返回 true，这时你可以通过其他任意方式提示（v2.5.7 新增）
            if(value === 'xxx'){
                alert('用户名不能为敏感词');
                return true;
            }
        },
        mgr: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value > 9999 || value < 1001){
                return "上司编号只能在 1001 ~ 9999 之间！";
            }
        },
        sal: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value > 50000 || value < 4500){
                return "工资只能在 4500 ~ 50000 之间！";
            }
        },
        comm: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value > 5000 || value < 1500){
                return "奖金只能在 1500 ~ 5000 之间！";
            }
        }
    });

});