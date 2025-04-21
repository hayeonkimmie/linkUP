<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 20.
  Time: 오전 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-overlay" id="settlementModal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="modal-title">[${project.title}] 정산 내역</span>
            <span class="modal-close" onclick="closeModal()">&times;</span>
        </div>
        <table class="settlement-table">
            <thead>
            <tr>
                <th>회차</th>
                <th>금액</th>
                <th>상태</th>
                <th>정산일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="settle" items="${settlements}">
                <tr>
                    <td>${settle.round}회차</td>
                    <td><fmt:formatNumber value="${settle.amount}" type="currency"/></td>
                    <td>
                        <span class="status ${settle.status eq '정산 완료' ? 'complete' : 'pending'}">
                                ${settle.status}
                        </span>
                    </td>
                    <td>${settle.settlementDate}</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3" style="text-align: right;"><strong>총 정산액</strong></td>
                <td>
                    <strong>
                        <fmt:formatNumber value="${fn:sum(settlements, 'amount')}" type="currency"/>
                    </strong>
                </td>
            </tr>
            </tfoot>
        </table>
        <button class="modal-confirm" onclick="closeModal()">확인</button>
    </div>
</div>