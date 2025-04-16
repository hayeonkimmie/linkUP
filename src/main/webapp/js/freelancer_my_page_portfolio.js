
window.onload = function () {
  // 수정 버튼 클릭 이벤트
  const editButtons = document.querySelectorAll(".actions button:first-child");
  editButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
      const card = e.target.closest(".portfolio-card");
      const title = card.querySelector("h3").innerText;
      alert(`"${title}" 포트폴리오를 수정합니다.`);
      // 예: 수정 페이지 이동
      // window.location.href = `/portfolio/edit?id=123`;
    });
  });

  // 삭제 버튼 클릭 이벤트
  const deleteButtons = document.querySelectorAll(".actions button:last-child");
  deleteButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
      const card = e.target.closest(".portfolio-card");
      const title = card.querySelector("h3").innerText;
      const confirmed = confirm(`"${title}" 포트폴리오를 정말 삭제하시겠습니까?`);
      if (confirmed) {
        card.remove();
      }
    });
  });
};
