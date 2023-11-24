<template>
    <div class="set">
        <div>비밀번호를 입력해주세요</div>
        <div><input type="password" class="set-inp"></div>
        <div>
            <RouterLink :to="{ name: 'upde' }">
                <div class="set-btn">확인</div>
            </RouterLink>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.set {
    width: 500px;
    background-color: var(--header);
    text-align: center;
    margin: 100px auto;
    border-radius: 20px;
    padding: 20px;
}

.set-inp {
    padding: 10px;
    border-radius: 10px;
    border: solid 2px var(--primary-200);
    width: 200px;
    margin: 15px;
}

.set-btn {
    background-color: var(--primary-200);
    width: 150px;
    margin: 0 auto;
    padding: 5px;
    border-radius: 10px;
    transition: 0.2s;
}

.set-btn:hover {
    background-color: var(--primary-100);
    color: var(--bg-100);
}
</style>