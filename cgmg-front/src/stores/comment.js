import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const comment_API = `http://localhost:8080/comment-api`;

export const useCommentStore = defineStore('comment', () => {
  const commentList = ref([]);


  //전체목록가져오기
  const getCommentList = function (id) {
    axios({
      method: "GET",
      url: `${comment_API}/comment/list/${id}`,
    }).then((response) => {
      commentList.value = response.data;
    });
  };

    //댓글 등록
  const createComment = function (comment) {
    axios({
      url: `${comment_API}/comment`,
      method: "POST",
      data: comment,
    })
      .then(() => {
        getCommentList(comment.postId)
      })
  };
    //댓글 수정
  const updatecomment = function (comment) {
    axios.put(`${comment_API}/comment`, comment).then(() => {
    });
  };
  //댓글 삭제
    const deletecomment = function (data, comment) {
    axios({
      url: `${comment_API}/comment`,
      method: "delete",
      params: data,
    })
      .then(() => {
        console.log("삭제성공")
        getCommentList(comment.postId)
      })
  };





  return { commentList, deletecomment, updatecomment, createComment, getCommentList
};
});

