import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const Exercise_API = `http://localhost:8080/user-api`;

export const useUserStore = defineStore('user', () => {
  const searchList = ref([]);
  const profile = ref({});
  const followerList = ref([]);
  const followingList = ref([]);
  
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
    }).then((response) => {
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
    }).then((response) => {
      getfollower(data.userId.value);
      getfollowing(data.followingId.value);
    });
  };

  return { getSearch, searchList, profile, getProfile, getfollower, getfollowing, followerList, followingList, followAdd, followDelete};
});

