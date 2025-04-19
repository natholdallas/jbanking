<script setup lang="ts">
import ComCtl from '@/components/ComCtl.vue'
import {
  CodepenB as ICodepenB,
  Css3B as ICss3B,
  GithubB as IGithubB,
  GitlabB as IGitlabB,
  Html5B as IHtml5B,
  JsB as IJsB,
  QqB as IQqB,
  RedditB as IRedditB,
  TelegramB as ITelegramB
} from '@vexip-ui/icons'
import { Blockquote, H1, H2, LayoutFooter, OL, P, type LayoutFooterLink } from 'vexip-ui'

const links: LayoutFooterLink[] = [
  {
    name: '帮助',
    children: [
      {
        name: 'Github',
        icon: IGithubB
      },
      {
        name: 'Gitlab',
        icon: IGitlabB
      },
      {
        name: 'Codepen',
        icon: ICodepenB
      }
    ]
  },
  {
    name: '联系我',
    children: [
      {
        name: 'INSANE-qq',
        icon: IQqB
      },
      {
        name: 'INSANE-reddit',
        icon: IRedditB
      },
      {
        name: 'INSANE-telegram',
        icon: ITelegramB
      }
    ]
  },
  {
    name: '技术栈',
    children: [
      {
        name: 'Html',
        icon: IHtml5B
      },
      {
        name: 'Css',
        icon: ICss3B
      },
      {
        name: 'Javascript',
        icon: IJsB
      }
    ]
  }
]
</script>

<template>
  <ComCtl content-class="flex flex-col h-full">
    <H1 top>INSANE-BANK</H1>
    <P>
      INSANE-BANK 是一个多银行管理平台, 通过绑定各个不同银行的银行卡, 可以实现在一个平台下使用多张银行卡进行转账, 并且
      INSANE-BANK 提供了一些常用的功能, 如贷款等
    </P>
    <H2 top>特性</H2>
    <OL>
      <li>支持多个银行</li>
      <li>界面简约</li>
      <li>跨平台系统, MacOs, Windows, Linux, Android</li>
    </OL>
    <Blockquote>兼容所有浏览器 (除 IE 外)</Blockquote>
    <LayoutFooter class="mt-auto" :links="links" />
  </ComCtl>
</template>

<style scoped></style>
