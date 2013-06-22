<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">

<title>Customer Profile</title>
<link rel="stylesheet" href="resources/custom.css" media="all"/>
<link rel="stylesheet" href="resources/jquery/css/jquery-ui-1.10.2.smoothness.min.css" media="all"/>
<link rel="stylesheet" href="resources/jqwidgets/css/jqx.base.css" type="text/css" />

<script src="resources/jquery/js/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/js/jquery-ui-1.10.2.smoothness.min.js"></script>
<script src="resources/jquery/js/jquery-validate.min.js"></script>

<script src="resources/jqwidgets/js/jqxcore.js"></script>
<script src="resources/jqwidgets/js/jqxdata.js"></script> 
<script src="resources/jqwidgets/js/jqxbuttons.js"></script>
<script src="resources/jqwidgets/js/jqxscrollbar.js"></script>
<script src="resources/jqwidgets/js/jqxmenu.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.selection.js"></script>
<script src="resources/jqwidgets/js/jqxtabs.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.selection.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.sort.js"></script> 
<script src="resources/jqwidgets/js/jqxwindow.js"></script>
<script src="resources/jqwidgets/js/jqxinput.js"></script>
<script src="resources/jqwidgets/js/jqxgrid.columnsresize.js"></script>

<script src="resources/jqwidgets/js/gettheme.js"></script>

<script src="resources/ProductProfile.js"></script>

<script>
	rowCount=0;
	contextPath = '<%= request.getContextPath() %>';	
</script>
</head>

<body>
<div id="accordionAll">
	<h3>Show all Products</h3>
	<div id="productAll">
		<button id="fetch" style="margin-right: 5px; font-size: 13px;">FETCH ALL</button><span id="waitText1"></span><button id="deleterowbutton" style="margin-right: 5px; font-size: 13px;">DELETE SELECTED</button><span id="waitText3"></span>
		<br><br>
		<div id='jqxWidget' style="float: left; width: 100%">
			<div id="jqxgrid"></div>
		</div>	
	</div>
	
	<h3>Add a Product</h3>
	<form id="productAddForm" action="#" method="POST">
	<div id="productAdd">
		<div id="accordionAdd">
			<h3>Customer Details</h3>
			<div id="productMain">
				<table>
					<tr><td><label for="productName">Product Short Name : </label></td><td><input type="text" id="productShortName"/></td></tr>
					<tr><td><label for="productName">Product Name : </label></td><td><input type="text" id="productName"/></td></tr>
					<tr><td><label for="productName">Product Price  : </label></td><td><input type="text" id="productPrice"/></td></tr>
					<tr><td><label for="productName">Product Image : </label></td><td><input type="text" id="productImagePath"/></td></tr>
				</table>
				<br>
			</div>
		</div>
		<br>
		<button id="saveButton" style="margin-right: 5px; font-size: 13px;">SAVE</button>
		<span id="waitText2"></span>
		<button id="resetAdd" type="reset" style="font-size: 13px;">CLEAR</button>
	</div>
	</form>
</div>

<div id="popupWindow" style="display: none;"> 
     <div>Edit</div>
     <input id="ed_productId" type="hidden" />
     <div style="overflow: hidden;">
         <table id="editTable">
             <tr>
                 <th colspan="5" align="left">Customer Details</th>
             </tr>
             <tr>
                 <td align="right">Name:</td>
                 <td align="left"><input id="ed_name"/></td>
                 <td align="right">Member Since:</td>
                 <td align="left"><input id="ed_memberSince" /></td>
                 <td align="right">Balance:</td>
                 <td align="left"><input id="ed_balance" /></td>
             </tr>
          </table>
     </div>
</div>

</body>
</HTML>
<script>
$(function(){
	$("#productMain").css( "height", "100%" );
});
</script>