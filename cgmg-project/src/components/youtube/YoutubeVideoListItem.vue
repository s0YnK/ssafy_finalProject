<template>
  <li @click="clickVideo">
    <img :src="video.snippet.thumbnails.default.url">
    <span>{{ video.snippet.title }}</span>
    <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${store.selectedVideo.id.videoId}`"
      title="YouTube video player" frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen></iframe>
  </li>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
});

const clickVideo = function () {
  store.clickVideo(props.video)
}


</script>

<style scoped></style>
