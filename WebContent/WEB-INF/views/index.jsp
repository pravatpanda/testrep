<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My First Grid</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="css/redmond/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/ui.jqgrid.css" />

<script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>

<script type="text/javascript">
var contextPath = "<%=request.getContextPath()%>";
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : "POST",
			url : contextPath + "/getColData",
			dataType : "json",
			success : function(result) {
				var colM = result.colModel, colN = result.colNames;

				$("#list").jqGrid({
					url : contextPath + "/getEntities",
					datatype : 'json',
					mtype : 'POST',
					jsonReader : {
						root : "response",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						id : "employee_id"
					},
					colNames : colN,
					colModel : colM,
					pager : 'pager',
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					sortname : 'employee_id',
					sortorder : 'asc',
					sorttype : "integer",
					sortable : true,
					viewrecords : true,
					gridview : true,
					viewsortcols : [ true, 'vertical', true ],
					toolbar : [ true, "top" ],
					//caption : 'My first grid',
					height : '100%',
					width : 1300
				});
				jQuery("#list").jqGrid('filterToolbar', {
					searchOperators : false
				});
				jQuery("#list").jqGrid('navGrid', '#pager', {
					"edit" : false,
					"add" : false,
					"del" : false,
					"search" : true,
					"refresh" : true,
					"view" : false,
					"excel" : true
				}, {}, {}, {}, {
					"drag" : false,
					"closeAfterSearch" : true,
					"closeOnEscape" : true,
					"multipleSearch" : true
				}, {
					"drag" : true,
					"resize" : true,
					"closeOnEscape" : true,
					"dataheight" : 150
				});
				jQuery('#list').jqGrid('navButtonAdd', '#pager', {
					id : 'pager_excel',
					caption : 'Export To Excel',
					title : 'Export To Excel',
					onClickButton : function(e) {
						try {
							jQuery("#list").jqGrid('excelExport', {
								tag : 'excel',
								mtype : 'POST',
								url : contextPath + '/excel'
							});
						} catch (e) {
							//window.location = 'grid.php?oper=excel';
						}
					},
					buttonicon : 'ui-icon-newwin'
				});
			},
			error : function(x, e) {
				alert(x.readyState + " " + x.status + " " + e.msg);
			}
		});
	});
</script>

</head>
<body>
	<table id="list" style="width: 100%;"></table>
	<div id="pager" style="width: 100%;"></div>
</body>
</html>
