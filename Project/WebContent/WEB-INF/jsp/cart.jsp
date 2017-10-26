<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
  <jsp:include page="/baselayout/head.html" />
  <jsp:include page="/baselayout/header.jsp" />
  </head>
  <br><br><br>
  <body>


  <!-- body -->
    <div class="container">


      <h1 align="center">買い物かご</h1>
			<br><br>
			<div class="container">
			<div class="row">
			  <div class="col-sm-4">
					<form align="right" action="#staticModal" method="POST">
						<input type="hidden" name="delete" value="">
						<!--<button class="btn btn-info" type="submit" name="action" >-->
							<button type="button" class="btn btn-info col-sm-8 col-sm-offset-5" data-toggle="modal" data-target="#staticModal" >削除</button>
						<!--<div style="width:120px;">削除</div>-->
						<!--href="#staticModal"-->
					</button>
				</form>
				</div>
				<!-- モーダルダイアログ -->
				<div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">

								<h4 class="modal-title">削除</h4>
							</div><!-- /modal-header -->
							<div class="modal-body">
								<p class="recipient">削除しました</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							</div>
						</div> <!-- /.modal-content -->
					</div> <!-- /.modal-dialog -->
				</div>




			  <div class="col-sm-6">
					<form align="right" action="buy.html" method="POST">
						<input type="hidden" name="item_id" value="">
						<button class="btn btn-info" type="submit" name="action"　>
						<div style="width:200px;">レジに進む</div>
					</button>
				</form>
				</div>
			</div>
		</div>
      <br><br>




			     <div class="container">
			       <h4>カートの商品<h4>
			     	<div class="row">

			         <div class="col-md-3">
			           <br>
			           <div class="card">
			               <div class="card-image">
			                 <a href="item.html">
			                <img  src="./img/fd400947.jpg"  width="260" height="250" 　/>
			                 </a>
			                 <div class="card-content">
			                 <br>
			                 <span class="card-title">美味しいもも</span>
			                 <br><br>
			                 10000000円


											 <p>
									    <input type="checkbox"  name="delete_item_id_list" value="" /> 削除
								     </p>
									 </div>

			              </div>
			             </div>
			           </div>


		   
			         <div class="col-md-3">
			           <br>
			           <div class="card">
			               <div class="card-image">
			                 <a href="item.html">
			               <img  src="./img/fd401266.jpg"  width="260" height="250" 　/>
			                </a>
			                <div class="card-content">
			                 <br>
			                 <span class="card-title">ぶどう</span>
			                 <br><br>
			                 10000000円

											 <p>
									    <input type="checkbox"  name="delete_item_id_list" value="" /> 削除
								     </p>
			               </div>
			              </div>
			             </div>
			           </div>
							 </div>
						 </div>

  </body>
</html>