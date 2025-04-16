// sidebar.js
window.addEventListener("DOMContentLoaded", () => {
  fetch("sidebar.html")
    .then(res => res.text())
    .then(data => {
      document.getElementById("sidebar-placeholder").innerHTML = data;

      // 드롭다운 토글 이벤트
      document.querySelectorAll('.category-title').forEach(title => {
        title.addEventListener('click', () => {
          title.classList.toggle('active');
          const sublist = title.nextElementSibling;
          sublist.classList.toggle('show');
        });
      });

      // 하위 항목 선택 시 강조
      document.querySelectorAll('.subcategory li').forEach(item => {
        item.addEventListener('click', () => {
          document.querySelectorAll('.subcategory li').forEach(i => i.classList.remove('selected'));
          item.classList.add('selected');
        });
      });
    });
});


// 아래 코드는 사이드바가 닫히지 않도록 하는 코드 입니다.
// window.addEventListener("DOMContentLoaded", () => {
//   fetch("sidebar.html")
//     .then(res => res.text())
//     .then(data => {
//       document.getElementById("sidebar-placeholder").innerHTML = data;

//       const ACTIVE_CATEGORY_KEY = 'activeCategoryTitles';
//       const SELECTED_ITEM_KEY = 'selectedSubcategoryItem';

//       // 불러와서 UI 반영
//       const activeTitles = JSON.parse(localStorage.getItem(ACTIVE_CATEGORY_KEY)) || [];
//       const selectedItemText = localStorage.getItem(SELECTED_ITEM_KEY);

//       document.querySelectorAll('.category-title').forEach(title => {
//         const sublist = title.nextElementSibling;

//         if (activeTitles.includes(title.textContent.trim())) {
//           title.classList.add('active');
//           sublist.classList.add('show');
//         }

//         title.addEventListener('click', () => {
//           title.classList.toggle('active');
//           sublist.classList.toggle('show');

//           // 상태 저장
//           const updatedTitles = [];
//           document.querySelectorAll('.category-title.active').forEach(activeTitle => {
//             updatedTitles.push(activeTitle.textContent.trim());
//           });
//           localStorage.setItem(ACTIVE_CATEGORY_KEY, JSON.stringify(updatedTitles));
//         });
//       });

//       document.querySelectorAll('.subcategory li').forEach(item => {
//         // 초기 로드 시 선택된 항목 표시
//         if (item.textContent.trim() === selectedItemText) {
//           item.classList.add('selected');
//         }

//         item.addEventListener('click', () => {
//           localStorage.setItem(SELECTED_ITEM_KEY, item.textContent.trim());
//         });
//       });
//     });
// });
