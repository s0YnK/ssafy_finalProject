import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const Exercise_API = `http://localhost:8080/user-api`;
const Notify_API = `http://localhost:8080/notify-api`; // 알림

export const useUserStore = defineStore('user', () => {
  const searchList = ref([]);
  const profile = ref({});
  const followerList = ref([]);
  const followingList = ref([]);
  const notifyList = ref([]); // 알림
  
  // 알림
  const getNotifyList = function (userId) {
    axios({
      method: "GET",
      url: `${Notify_API}/notify/list`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      notifyList.value = response.data;
    });
  };
  
  const getSearch = function (word) {
    axios({
      method: "GET",
      url: `${Exercise_API}/user/search`,
      params: {
        word: word,
      },
    }).then((response) => {
      searchList.value = response.data;
    });
  };
  const getProfile = function (userId) {
    axios({
      method: "GET",
      url: `${Exercise_API}/user/profile`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      profile.value = response.data;
    });
  };
  const getfollower = function (userId) {
    axios({
      method: "GET",
      url: `${Exercise_API}/user/list/follower`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      followerList.value = response.data;
    });
  };
  const getfollowing = function (userId) {
    axios({
      method: "GET",
      url: `${Exercise_API}/user/list/following`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      followingList.value = response.data;
    });
  };
  const followAdd = function (data) {
    axios({
      method: "post",
      url: `${Exercise_API}/user/follow`,
      data: data
    }).then(() => {
      getfollowing(data.followingId.value);
      getfollower(data.userId.value);
      window.location.href = `http://localhost:5173/otherProfile/${data.followingId}`
    });
  };
  const followDelete = function (data) {
    axios({
      method: "delete",
      url: `${Exercise_API}/user/follow`,
      data: data
    }).then(() => {
      getfollower(data.userId.value);
      getfollowing(data.followingId.value);
    });
  };


      //회원정보 수정
  const updateUser = function (data) {
    axios.put(`${Exercise_API}/user`, data).then(() => {
      console.log(data)
      console.log("회원정보 수정 완료")
      data.password = "";
      localStorage.setItem("loginUser", JSON.stringify(data));
      window.location.href = `http://localhost:5173/`
    });
  };
    //회원 탈퇴
  const deleteUser = function (data) {
    axios({
      method: "delete",
      url: `${Exercise_API}/user`,
      params: {
        userId: data,
      }
    }).then(() => {
      window.location.href = `http://localhost:5173/`
    });
  };

  return { getSearch, searchList, profile, getProfile, getfollower, getfollowing, followerList, followingList, followAdd, followDelete,
      updateUser, deleteUser, getNotifyList, notifyList
  };
});

