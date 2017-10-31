<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<title>商品マスタ一覧</title>
<jsp:include page="/baselayout/head.html" />
<jsp:include page="/baselayout/header.jsp" />
  <body>

      <br><br><br>

    <!-- body -->
    <div class="container">



      <h1 align="center">商品マスタ一覧</h1>
      <div class="text-right">
        <a href="itemcreate.html">商品登録</a>
      </div>


      <div class="panel-body">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">検索条件</div>
            </div>
            <div class="panel-body">
              <form method="post" action="#" class="form-horizontal">
                <div class="form-group">
                  <label for="code" class="control-label col-sm-2">商品ID</label>
                  <div class="col-sm-6">
                    <input type="text" name="item_id" id="item/id" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="control-label col-sm-2">商品名</label>
                  <div class="col-sm-6">
                    <input type="text" name="item_name" id="item_name" class="form-control"/>
                  </div>
                </div>

                <div class="form-group">
                  <label for="continent" class="control-label col-sm-2">登録日</label>
                  <div class="row">
                    <div class="col-sm-2">
                      <input type="date" name="date-start" id="date-start" class="form-control" size="30"/>
                    </div>
                    <div class="col-xs-1 text-center">
                      ~
                    </div>
                    <div class="col-sm-2">
                      <input type="date" name="date-end" id="date-end" class="form-control"/>
                    </div>
                </div>
                </div>
                <div class="text-right">
                  <button type="submit" value="検索" class="btn btn-primary form-submit">検索</button>
                </div>
              </form>


          </div>
        </div>

        <div class="table-responsive">
             <table class="table table-striped table-bordered" class="table table-bordered">
               <thead>
                 <tr>
                   <th>商品ID</th>
                   <th>商品名</th>
                   <th>登録日</th>
                   <th>価格</th>
                   <th>購入数</th>
                   <th>カテゴリ</th>
                   <th>ポイント</th>
                   <th></th>
                 </tr>
               </thead>
               <tbody>
                 <tr>
                   <td>id0001</td>
                   <td>ぶどう</td>
                   <td>1989年04月20日</td>
                   <td>1000円</td>
                   <td>345</td>
                   <td>果物</td>
                   <td>90pt</td>
                   <td>
                     <a class="btn btn-primary" href="itemdetailmanagement.html">詳細</a>
                     <a class="btn btn-success" href="itemupdate.html">更新</a>
                     <a class="btn btn-danger" href ="itemdelete.html">削除</a>
                   </td>
                 </tr>
                 <tr>
                   <td>id0002</td>
                   <td>もも</td>
                   <td>2001年11月12日</td>
                   <td>1000円</td>
                   <td>354367</td>
                   <td>果物</td>
                   <td>90pt</td>
                   <td>
                     <a class="btn btn-primary" href="itemdetailmanagement.html">詳細</a>
                     <a class="btn btn-success" href="itemupdate.html">更新</a>
                     <a class="btn btn-danger" href ="itemdelete.html">削除</a>
                   </td>
                 </tr>
                 <tr>
                   <td>id0003</td>
                   <td>机</td>
                   <td>2000年01月01日</td>
                   <td>44444円</td>
                   <td>56845</td>
                   <td>家具</td>
                   <td>90pt</td>
                   <td>
                     <a class="btn btn-primary" href="itemdetailmanagement.html">詳細</a>
                     <a class="btn btn-success" href="itemupdate.html">更新</a>
                     <a class="btn btn-danger" href ="itemdelete.html">削除</a>
                   </td>
                 </tr>
               </tbody>
             </table>
           </div>
         </div>
      </div>
      <div class="text-center">
        <ul class="pagination">
        <li><a href="#">≪ 前へ</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">6</a></li>
        <li><a href="#">次へ ≫</a></li>
      </ul>
     </div>
    </div>

  </body>
</html>