fetch(contextPath + "/headerLogin")
    .then(res => res.text())
    .then(data => {
        document.getElementById("header-login-placeholder").innerHTML = data;

        const profileToggle = document.querySelector('#header-login-placeholder .profile-toggle');
        const profileMenu = document.querySelector('#header-login-placeholder .profile-menu');


        if (profileToggle && profileMenu) {
            profileToggle.addEventListener('click', (e) => {
                e.stopPropagation();

                const isVisible = profileMenu.style.display === 'block';
                profileMenu.style.display = isVisible ? 'none' : 'block';
            });

            document.addEventListener('click', (e) => {
                if (!profileToggle.contains(e.target) && !profileMenu.contains(e.target)) {
                    profileMenu.style.display = 'none';
                }
            });
        } else {
            console.warn('❌ profileToggle 또는 profileMenu 못 찾음!');
        }
    });
