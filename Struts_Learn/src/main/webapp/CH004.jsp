<%--
	作業代號:CH004
	作業名稱:通關疑義暨權責機關答覆聯絡單追蹤作業
	程式代號:CH004
	
	JS Function描述:
		function doBeforeQuery2( name, debug ) 將查詢單位中文組字，列印值使用
		function doAfterQuery2( name, debug ) 檢查有沒有資料，有則顯示[列印]按鈕
		function doAfterReset2( name, debug ) 將下拉式選單關別欄位之後清空，僅保留預設值
		
	欄位驗證:
	    Validation:

	異動紀錄:
	Company: Tradevan Co.
	Author: 6651
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="customs" uri="/customs"%>
<%@ taglib prefix="sb" uri="/saab-tags"%>
<script type="text/javascript" src="<s:url value="/js/new/utils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/keyUtils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/grid2Utils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/tabUtils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/validateAdditional.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/stringUtils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/dynDateTime/dynDateTime.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/dynDateTime/calendar-big5.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/new/reportUtils.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/CA/CAUtil.js"/>"></script>

<link href="css/dynDateTime/calendar-blue2.css" rel="stylesheet" type="text/css" />

<table align="center" width="900px">
	<tr height="80%">
		<td align="center">
			<sj:tabbedpanel id="mytabs" animate="true" collapsible="false" useSelectedTabCookie="false">
				<sj:tab id="tab1" target="divTab1" label="追蹤"/>
				
				<div id="divTab1">
				<form id="master_dataForm2">
					<fieldset align="left" style="border-style:ridge; border-color:white;">
					 	<legend>輸入條件</legend>
						<table width="900px" border="0" cellpadding="3" cellspacing="3" class="NoNewLine">
							<tr>
								<td><s:label value="進/出口別：" />
									<s:select id="IE_TYPE" name="ieType" list="#{'IG':'一般進口(IG)','EG':'一般出口(EG)','IX':'簡易進口(IX)','EX':'簡易出口(EX)','IP':'郵包進口(IP)','EP':'郵包出口(EP)'}" 
											  headerKey="" headerValue="請選擇" alt="進/出口別"  style="width:102px;"/>
							    </td>
							</tr>
							<tr id="ISSUE_UNIT">
								<td width="200px">
								<s:label value="關別："/>
								<s:select 
									id="customs"
	 								name="custCd"
	 								list="bfCustCdList"
	 								headerKey=""
	 								headerValue="請選擇"
	 								listKey="key"
	 								listValue="value"
	 								cssStyle="width:135px"
								></s:select>
 								</td>

								<td width="170px"><s:label value="單位：" />
									<s:select id="unitId" name="unitId" list="#{'':'請選擇'}" alt="單位" style="width:120px;"/>
								</td>
												
								<td width="170px"><s:label value="課別：" />
									<s:select id="wrkUnit" name="wrkUnit" list="#{'':'請選擇'}" alt="課別" style="width:120px;"/>
								</td>
								
								<td width="170px"><s:label value="股別：" />
									<s:select id="team" name="team" list="#{'':'請選擇'}" alt="股別" style="width:120px;"/>
								</td>
								<s:textfield id="ISSUE_UNIT_CHINESE" name="issueUnitChinese" alt="查詢單位"  cssStyle="width:180px" hidden="true"/>
								
								<td align="right"><s:label value="承辦人：" />
									<s:textfield id="evOfficerId" name="evOfficerId" maxlength="10" alt="承辦人"  cssStyle="width:70px"/>
								</td>
							</tr>
							<tr>
								<td  colspan="3">
									<s:label value="發文日期 起："/>
									<s:textfield id="STARTD" name="startD" size="10" alt="發文日期 起："/>
									<s:label value="　發文日期 訖："/>
									<s:textfield id="ENDD" name="endD" size="10" alt="發文日期 訖："/>
								</td>
							</tr>
							<tr align="left" >
								<td colspan="3">
									<div id="dealClosed" class="checkboxGroup" style="display:inline;">
										<s:checkbox id="DEAL_CLOSED" name="dealClosed"/><label for="DEAL_CLOSED">已結案件</label>
									</div>
									<div id="dealNotYet" class="checkboxGroup" style="display:inline;">
										<s:checkbox id="DEAL_NOT_YET" name="dealNotYet"/><label for="DEAL_NOT_YET">已發文未結案</label>
									</div>
									<div id="docnoNotYet" class="checkboxGroup" style="display:inline;">
										<s:checkbox id="DOC_NOT_YET" name="docnoNotYet"/><label for="DOC_NOT_YET">已取文號未發文</label>
									</div>
								</td>
								<td align="right" colspan="3"><s:label value="排 序 條 件：" />
									<s:select id="SORT_STATUS" name="sortStatus" list="#{'DOC_NO':'發文文號','DOC_DATE':'發文日期'}" 
											   alt="排序條件" style="width:120px;"/>
								</td>
							</tr>
							<tr align="center">
								<td colspan="6">
								<sb:permission privilegeId="CH004_JSP_QUERY">
									<input type="button" id="master_searchButton2" class="btnFixed" value="F6 查詢"/>
								</sb:permission>								
								<sb:permission privilegeId="CH004_JSP_PRINT">
									<input type="button" id="master_printButton2" class="btnFixed" value="F7 列印" disabled/>
								</sb:permission>								
									<input type="button" id="master_resetButton2" class="btnFixed" value="SF1 清除"/>
								</td>
							</tr>
						</table>		   		
					</fieldset>
					<br>
					<sjg:grid
						id="master_grid2"
						dataType="json"
						gridModel="gridModel"
						rownumbers="true"
						rowNum="10"
						height="246"
						width="900"
						shrinkToFit="false"
						onPagingTopics="master_pagingTopics2"
						onSelectRowTopics="master_selectRowTopics2"
						pager="true"
						rowList="10,20,30"
						viewrecords="true">
						<sjg:gridColumn name="DOC_NO" index="DOC_NO" title="發文文號"  sortable="true" align="center" width="100" />
						<sjg:gridColumn name="DOC_DATE" index="DOC_DATE" title="發文日期"  sortable="true" align="center" width="100"/>												
						<sjg:gridColumn name="REPLY_UNIT" index="REPLY_UNIT" title="回 文 單 位"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="DECL_NO" index="DECL_NO" title="報單號碼"  sortable="false" align="center" width="120" />
						<sjg:gridColumn name="MAWB" index="MAWB" title="主提單號"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="HAWB" index="HAWB" title="分提單號"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="IE_REGS" index="IE_REGS" title="輸入(出)規定"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="TAX" index="TAX" title="內地稅"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="DEAL_STS" index="DEAL_STS" title="辦理情形"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="DEAL_DY" index="DEAL_DY" title="辦理天數"  sortable="false" align="center" width="100" />
						<sjg:gridColumn name="OFFICER2" index="OFFICER2" title="承辦人"  sortable="false" align="center" width="100" />											
					</sjg:grid>
				</form>
				<form id="master_gridCondition2">
					<input type="hidden" id="searchUrl" value="CH004!queryCSBFAMM"/>
					<input type="hidden" id="addUrl" value="CH004!addCSBFAMM"/>
					<input type="hidden" id="updateUrl" value="CH004!updateCSBFAMM"/>
					<input type="hidden" id="deleteUrl" value="CH004!deleteBothTables"/>
					<input type="hidden" id="printUrl" value="CH004!print"/>
					<input type="hidden" name="rows" id="rows"/>
					<input type="hidden" name="page" id="page"/>
					<input type="hidden" id="debug" value="false"/>
					<input type="hidden" id="isFillDataToDataForm" value="false"/>
				</form>
				</div>
			</sj:tabbedpanel>
		</td>
	</tr>
</table>

<table align="center">
	<tr>
		<td>
			<s:include value="../newStatus.jsp"/>
		</td>
	</tr>
</table>
<script>
$(function() {
	//日期元素初始化
	var field = "#STARTD, #ENDD";
	$(field).mask('999/99/99');
    $(field).dynDateTime({
		zeroAndNine:false
	});
	
	$('#master_printButton2').viewReport( {
		url : 'CH004!print',
		module : 'ch',
		jasperFileName : 'CH004_RPT',
		conditionFormSelector : $('#master_gridCondition2')
	});
})
function doBeforeQuery2( name, debug ){
	var tempStr = "";
	//查詢單位
	if($('#unitId',$('#ISSUE_UNIT')).val()!=""){		
		tempStr += $('#unitId',$('#ISSUE_UNIT')).find('option:selected').text().split(':')[1];
	};
	if($('#wrkUnit',$('#ISSUE_UNIT')).val()!=""){
		tempStr += $('#wrkUnit',$('#ISSUE_UNIT')).find('option:selected').text().split(':')[1];
	};
	if($('#team',$('#ISSUE_UNIT')).val()!=""){		
		tempStr += $('#team',$('#ISSUE_UNIT')).find('option:selected').text().split(':')[1];
	};
	$('#ISSUE_UNIT_CHINESE').val(tempStr);
	return true;
}
function doAfterQuery2( name, debug ){

	id = common.grid.getIds($('#master_grid2'));
	if(id!=""){
		$('#master_printButton2').attr("disabled",false);
	}else{
		$('#master_printButton2').attr("disabled",true);
	}
	
	return true;
}
function doAfterReset2(name, debug){
		if(name=='master'){
			<%--清除當下選擇以外的option項目=>僅保留預設值的「請選擇」--%>
			$('select[id="unitId"] option, select[id="wrkUnit"] option, select[id="team"] option', $('#ISSUE_UNIT')).not(':selected').remove();
		}
	}
</script>