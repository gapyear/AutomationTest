<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				<title>报告信息</title>
				<style>
					body{
					margin:0;
					background: #F0F0F0;
					}
					ul{
					list-style: none;
					}
					a{
					text-decoration: none;
					}
					header {
					height:40px;
					font-size:14px;
					color: #02DF82;
					font-family: "微软雅黑";
					background: #FFFFFF;
					font-weight: bold;
					text-align: center;
					line-height: 40px;
					}
					.normalFont {
					font-size:10px;
					font-family: "微软雅黑";
					font-weight: bold;
					}
					.headerFont {
					color: #3c3c3c;
					}
					.passFont {
					color: #02DF82;
					}
					.failFont {
					color:#FF0000;
					}
					.warningFont {
					color:#ffd306;
					}
					.module {
					margin:20px 10px;
					//height:400px;
					}
					.reportDetail {
					background: #ADADAD;
					padding-top:2px;
					}
					.reportDetail p {
					padding:0 0 0 10px;
					margin:0 0 1px 0;
					height:30px;
					background:#fff;
					line-height: 30px;
					}
					.detailItem{
					background: #fff;
					}
					.formleft {
					width:50%;
					float:left;
					}
					.graphright {
					width:50%;
					float:left;
					}
					.clearfix:after {
					content:"";
					display: block;
					clear: both;
					}
					.formleft li{
					margin:10px 30px;
					}
					.formleft li span {
					display: inline-block;
					width:250px;
					height:18px;
					border:1px solid #D0D0D0;
					margin-left: 10px;
					line-height:18px;
					padding-left:5px;
					font-weight: normal;
					}
					.caseDetail {
					padding:10px 8px;
					}
					.caseDetail table{
					border:1px solid #D0D0D0;
					width:100%;
					border-collapse: collapse;
					}
					.caseDetail table thead {
					background: #F0F0F0;
					}
					.caseDetail table tr{
					height:26px;
					}
					.caseDetail table td{
					padding-left: 6px;
					}
					.caseDetail table td:nth-child(1){
					width:80px;
					}
					.caseDetail table td:nth-child(2){
					width:50%;
					}
					.caseDetail table a {
					width:60px;
					height:16px;
					border-radius: 2px;
					background: #02DF82;
					font-size: 10px;
					font-family: "微软雅黑";
					color: #fff;
					padding:2px 4px;
					}
					.mask{
					width:100%;
					height:100%;
					position: absolute;
					top:0;
					left:0;
					z-index:1000;
					background: #080808;
					opacity: 0.2;
					display: none;
					}
					.stepDetail{
					width:80%;
					position:absolute;
					top:25%;
					left:10%;
					z-index: 1001;
					display:none;
					}
					.stepDetail .close {
					margin-top:10px;
					}
					.stepDetail .close a{

					height:16px;
					border-radius: 2px;
					background: #02DF82;
					font-size: 10px;
					font-family: "微软雅黑";
					color: #fff;
					padding:2px 10px;
					float:right;
					}
				</style>
				<script language="JavaScript">
					<xsl:comment>
						<![CDATA[
							window.onload = function(){
				
				var oDiv = document.getElementById("caseDetail");
				
				var aA = oDiv.getElementsByTagName("a");
				
				var oBody = document.getElementsByTagName("body")[0];
				
				var oMask = null;
				
				for(var i=0;i<aA.length;i++){
				
					aA[i].num = i;
					
					aA[i].onclick = function(){
									
						oMask = document.querySelector("div.mask");
						
						oMask.style.display = "block";
						
						var template = document.getElementById("stepDetail");
						
						template.num = this.num;
						
						//console.log(this.num)
						setXmlNode("testcase","isShow","true",template.num);
				
						template.style.display = "block";
						
						var oClose = stepDetail.querySelector(".close a");

						oClose.onclick = function() {
							
							template.style.display = "none";
							
							oMask = document.querySelector("div.mask");
							
							oMask.style.display="none";
							
							setXmlNode("testcase","isShow","false",template.num);
							
						}
						
					}
				}
				
				function setXmlNode(node,key,value,num){
				
					
					//加载XML文件。
					var xmlDom=createXMLDom("report.xml");
					//console.log(xmlDom)
					
					//获得根节点
					//var root=xmlDom.documentElement;
					
					//console.log(root)
					
					var node=xmlDom.getElementsByTagName(node)[num];
			
					node.setAttribute(key,value);
					
					xmlDom.save("report.xml");
					console.log(node.getAttribute(key));
					
					} 
					
				function createXMLDom(filename) {

					var xmlDoc = null;
	                try //Internet Explorer
					  {
					  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
					  }
					catch(e)
					  {
					  try //Firefox, Mozilla, Opera, etc.
					    {
					     xmlDoc=document.implementation.createDocument("","",null);
					    }
					  catch(e) {alert(e.message)}
					  }
					  
					  try{
					  	xmlDoc.async=false;
						xmlDoc.load(filename);
						return(xmlDoc);
					  }catch(e){
					  	alert(e.message)
					  }
	                return null;

				}
				
				
			}
						]]>
					</xsl:comment>
				</script>
			</head>

			<body>

				<header>自动化测试报告</header>
				<section class="module">
					<div class="reportDetail">
						<p class="normalFont headerFont">报告汇总</p>
						<div class="detailItem clearfix">
							<div class="formleft">
								<ul>
									<li>
										<label class="normalFont passFont">测试平台:</label>
										<span class="normalFont">自动化测试平台</span>
									</li>
									<li>
										<label class="normalFont passFont">用例总数:</label>
										<span class="normalFont">5</span>
									</li>
									<li>
										<label class="normalFont passFont">用例通过:</label>
										<span class="normalFont">2</span>
									</li>
									<li>
										<label class="normalFont failFont">用例失败:</label>
										<span class="normalFont">3</span>
									</li>
									<li>
										<label class="normalFont warningFont">用例跳过:</label>
										<span class="normalFont">0</span>
									</li>
									<li>
										<label class="normalFont passFont">开始时间:</label>
										<span class="normalFont">2019-03-28 14:47:31</span>
									</li>
									<li>
										<label class="normalFont passFont">运行时间:</label>
										<span class="normalFont">5ms</span>
									</li>
								</ul>

							</div>
							<div class="graphright">


							</div>
						</div>
					</div>
				</section>
				<section class="module">
					<div class="reportDetail">
						<p class="normalFont headerFont">详细数据</p>
						<div class="detailItem caseDetail" id="caseDetail">
							<table cellspacing="8" border="1" class="normalFont">
								<thead>
									<tr>
										<td>用例编号</td>
										<td>用例名称</td>
										<td>用例步骤个数</td>
										<td>耗时</td>
										<td>结果</td>
										<td>查看步骤</td>
									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="report/testcase">
										<tr>
											<td>
												<xsl:value-of select="num" />
											</td>
											<td>
												<xsl:value-of select="name" />
											</td>
											<td>
												<xsl:value-of select="count(steps/step)" />
											</td>
											<td>2ms</td>
											<td>
												<xsl:value-of select="casestatus" />
											</td>
											<td>
												<a href="javascript:;">查看</a>
											</td>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</div>
					</div>
				</section>
				<div class="detailItem caseDetail stepDetail" id="stepDetail">
					<table cellspacing="8" border="1" class="normalFont">
						<thead>
							<tr>
								<td>步骤编号</td>
								<td>步骤名称</td>
								<td>关键字</td>
								<td>参数</td>
								<td>结果</td>
							</tr>
						</thead>
						<tbody>
							<xsl:for-each select="report/testcase[@isShow='true']/steps/step">
								<tr>
									<td>
										<xsl:value-of select="id" />
									</td>
									<td>
										<xsl:value-of select="name" />
									</td>
									<td>
										<xsl:value-of select="keyword" />
									</td>
									<td>
										<xsl:for-each select="args">
											<xsl:value-of select="value" />
										</xsl:for-each>
									</td>
									<td>
										<xsl:value-of select="stepstatus" />
									</td>
								</tr>

							</xsl:for-each>
						</tbody>
					</table>
					<div class="close">
						<a href="javascript:;">关闭</a>
					</div>
				</div>
				<div class="mask"></div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>