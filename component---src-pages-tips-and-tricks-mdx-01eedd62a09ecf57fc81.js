(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{378:function(e,t,a){"use strict";a.r(t),a.d(t,"_frontmatter",function(){return o}),a.d(t,"default",function(){return b});a(9),a(5),a(6),a(3),a(8),a(2),a(1);var r=a(104),n=a(390);function c(){return(c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r])}return e}).apply(this,arguments)}var o={},s={_frontmatter:o},i=n.a;function b(e){var t=e.components,a=function(e,t){if(null==e)return{};var a,r,n={},c=Object.keys(e);for(r=0;r<c.length;r++)a=c[r],t.indexOf(a)>=0||(n[a]=e[a]);return n}(e,["components"]);return Object(r.b)(i,c({},s,a,{components:t,mdxType:"MDXLayout"}),Object(r.b)("p",null,"These are the tips and tricks working with the IBM FHIR Server. "))}b.isMDXComponent=!0},388:function(e){e.exports={data:{site:{pathPrefix:"/FHIR"}}}},389:function(e){e.exports={data:{site:{siteMetadata:{repository:{baseUrl:"https://github.com/IBM/FHIR",subDirectory:"/docs"}}}}}},390:function(e,t,a){"use strict";a(34),a(23);var r=a(2),n=a(388),c=a(1),o=a.n(c),s=a(193),i=a(90),b=a.n(i),l=a(71),p=a(122),u=a(4),d=a.n(u),h=a(368),m=function(e){var t,a=e.children,n=e.title,c=e.tabs,o=void 0===c?[]:c,s=e.shouldHideHeader;return Object(r.b)("div",{className:d()((t={},t[h.pageHeader]=h.pageHeader,t[h.pageHeaderSticky]=o.length,t[h.pageHeaderShifted]=s,t))},Object(r.b)("div",{className:"bx--grid"},Object(r.b)("div",{className:"bx--row"},Object(r.b)("div",{className:"bx--col-lg-12"},Object(r.b)("h1",{id:"page-title",className:h.text},n)))),a)},v=a(389),O=a(369),f=function(e){var t=e.relativePagePath,a=e.repository,n=v.data.site.siteMetadata.repository,c=a||n,o=c.baseUrl,s=o+"/tree/master"+c.subDirectory+"/src/pages"+t;return o?Object(r.b)("div",{className:"bx--row "+O.row},Object(r.b)("div",{className:"bx--col"},Object(r.b)("a",{className:O.link,href:s},"Edit this page on GitHub"))):null},j=a(194),g=a(29),x=a(370);var y=function(e){var t,a;function n(){return e.apply(this,arguments)||this}return a=e,(t=n).prototype=Object.create(a.prototype),t.prototype.constructor=t,t.__proto__=a,n.prototype.render=function(){var e=this.props,t=e.tabs,a=e.slug,n=a.split("/").filter(Boolean).slice(-1)[0],c=t.map(function(e){var t,c=b()(e,{lower:!0}),o=c===n,s=a.replace(n,c);return Object(r.b)("li",{key:e,className:d()((t={},t[x.selectedItem]=o,t),x.listItem)},Object(r.b)(g.Link,{className:x.link,to:""+s},e))});return Object(r.b)("div",{className:x.tabsContainer},Object(r.b)("div",{className:"bx--grid"},Object(r.b)("div",{className:"bx--row"},Object(r.b)("div",{className:"bx--col-lg-12 bx--col-no-gutter"},Object(r.b)("nav",null,Object(r.b)("ul",{className:x.list},c))))))},n}(o.a.Component),w=a(195);t.a=function(e){var t=e.pageContext,a=e.children,c=e.location,o=t.frontmatter,i=void 0===o?{}:o,u=t.relativePagePath,d=i.tabs,h=i.title,v=Object(l.c)(),O=!!d&&"down"===v,g=n.data.site.pathPrefix,x=g?c.pathname.replace(g,""):c.pathname,N=d?x.split("/").slice(-1)[0]||b()(d[0],{lower:!0}):"";return Object(r.b)(p.a,{shouldHideHeader:O,homepage:!1},Object(r.b)(m,{shouldHideHeader:O,title:h,label:"label",tabs:d},d&&Object(r.b)(y,{slug:x,tabs:d,currentTab:N})),Object(r.b)(w.a,{padded:!0},a,Object(r.b)(f,{relativePagePath:u})),Object(r.b)(j.a,{pageContext:t,location:c,slug:x,tabs:d,currentTab:N}),Object(r.b)(s.a,null))}}}]);
//# sourceMappingURL=component---src-pages-tips-and-tricks-mdx-01eedd62a09ecf57fc81.js.map