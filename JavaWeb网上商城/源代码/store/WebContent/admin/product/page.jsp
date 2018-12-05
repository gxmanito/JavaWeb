<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--分页 -->
<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
	<ul class="pagination" style="text-align: center; margin-top: 10px;">
		<!-- 判断是否是第一页 -->
		<c:if test="${pb.pageNumber == 1 }">
			<li class="disabled"><a href="javascript:void(0)"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<!-- 不是第一页 -->
		<c:if test="${pb.pageNumber != 1 }">
			<li class="disabled"><a
				href="${pageContext.request.contextPath}/adminProduct?method=findByPage?pageNumber=${pb.pageNumber-1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<!-- 显示所有页码 -->
		<c:forEach begin="1" end="${pb.totalPage }" var="n">
			<!-- 判断是否是当前页 -->
			<c:if test="${pb.pageNumber == n }">
				<li class="active"><a href="javascript:void(0)">${n }</a></li>
			</c:if>
			<c:if test="${pb.pageNumber != n }">
				<li><a
					href="${pageContext.request.contextPath}/adminProduct?method=findByPage?pageNumber=${n}">${n }</a></li>
			</c:if>
		</c:forEach>

		<!-- 判断是否是最后一页 -->
		<c:if test="${pb.pageNumber == pb.totalPage }">
			<li class="disabled"><a href="javascript:void(0)"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		<!-- 不是最后一页 -->
		<c:if test="${pb.pageNumber != pb.totalPage }">
			<li class="disabled"><a
				href="${pageContext.request.contextPath}/adminProduct?method=findByPage?pageNumber=${pb.pageNumber+1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>
</div>
<!-- 分页结束=======================        -->