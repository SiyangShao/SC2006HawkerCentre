import React from "react";

export function confirmLogout() {
    if (window.confirm("Are you sure you want to logout?")) {
        sessionStorage.setItem("isLoggedIn", "false");
        sessionStorage.clear();
        window.location.href = "/welcome";
    }
}
