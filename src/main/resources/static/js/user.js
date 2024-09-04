document.addEventListener("DOMContentLoaded", function() {
    const checkUserIdButton = document.getElementById("checkUserId");
    const userIdInput = document.getElementById("USER_ID");
    const passwordInput = document.getElementById("PASSWORD");
    const nameInput = document.getElementById("NAME");
    const ageInput = document.getElementById("AGE");
    const genderSelect = document.getElementById("GENDER");
    const phoneInput = document.getElementById("PHONE");
    const registerButton = document.querySelector("button[type='submit']");

    let isUserIdValid = false;

    function checkFormValidity() {
        const isFormValid =
            userIdInput.value.trim() !== "" &&
            passwordInput.value.trim() !== "" &&
            nameInput.value.trim() !== "" &&
            ageInput.value.trim() !== "" &&
            genderSelect.value !== "" &&
            phoneInput.value.trim() !== "" &&
            isUserIdValid;

        registerButton.disabled = !isFormValid;
    }

    checkUserIdButton.addEventListener("click", function() {
        const userId = userIdInput.value;

        if (!userId) {
            alert("아이디를 입력하세요.");
            return;
        }

        // 아이디 중복 여부 확인
        fetch(`/checkUserId?userId=${userId}`)
            .then(response => response.json())
            .then(data => {
                if (data) {
                    alert("이미 사용 중인 아이디입니다.");
                    isUserIdValid = false;
                } else {
                    alert("사용 가능한 아이디입니다.");
                    isUserIdValid = true;
                }
                checkFormValidity();
            })
            .catch(error => {
                console.error("Error:", error);
                alert("오류");
                isUserIdValid = false;
                checkFormValidity();
            });
    });

    [userIdInput, passwordInput, nameInput, ageInput, genderSelect, phoneInput].forEach(input => {
        input.addEventListener("input", checkFormValidity);
    });

    checkFormValidity();
});
