//package com.ispiroglu.redditclone.config
//
//import com.ispiroglu.redditclone.domain.model.Post
//import com.ispiroglu.redditclone.domain.model.Redditor
//import com.ispiroglu.redditclone.domain.model.Subreddit
//import com.ispiroglu.redditclone.service.PostService
//import com.ispiroglu.redditclone.service.RedditorService
//import com.ispiroglu.redditclone.service.SubredditService
//import io.github.serpro69.kfaker.Faker
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//import java.time.Instant
//
//@Component
//class SampleDataLoader(
//    private val postService: PostService,
//    private val redditorService: RedditorService,
//    private val subredditService: SubredditService,
//): CommandLineRunner {
//    private val faker: Faker = Faker()
//
//    private val desc: String = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fermentum rhoncus nisl eu volutpat. Maecenas vestibulum ac eros sit amet semper. Nulla condimentum est sit amet sapien posuere cursus. Nulla magna metus, egestas sit amet nisl vel, malesuada convallis quam. Vestibulum quis blandit velit. Vestibulum non pulvinar felis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Maecenas in mattis sem, eu imperdiet nibh. Morbi ultrices neque sodales, ornare mauris at, dictum ligula. Sed luctus porta orci, sit amet blandit justo vulputate id. Praesent tempor erat enim, id venenatis nulla tempus eget. Maecenas varius justo elit, id convallis tortor posuere sed. In dapibus nisl mi, sit amet vehicula felis consequat a.
//
//Integer et suscipit eros. Sed luctus tellus sit amet auctor porttitor. Praesent a commodo orci, eget finibus ligula. Donec vel mi ut neque accumsan sodales vel in magna. Proin sapien elit, luctus ac tellus a, dignissim rutrum lorem. Maecenas non turpis consectetur justo rhoncus aliquet. Suspendisse in lectus nec nulla suscipit malesuada eu a lectus. In rutrum justo tortor, quis maximus purus commodo vel.
//
//Aliquam pretium nulla vitae risus consequat, nec lacinia urna laoreet. Mauris at nulla quis est suscipit laoreet ac vel lacus. Morbi leo enim, iaculis ac mi non, volutpat semper elit. Vivamus vestibulum elementum felis. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus finibus erat ac interdum congue. Aliquam a placerat ante, eu finibus sem.
//
//Fusce id turpis malesuada, tempus lorem eu, maximus augue. Praesent quis placerat quam, ut euismod lectus. Vivamus fermentum velit eget condimentum vehicula. Praesent vel quam non neque aliquam efficitur. Nunc vestibulum faucibus ullamcorper. Praesent vel mollis felis. Donec urna augue, eleifend et suscipit a, congue a elit. Cras porttitor congue venenatis. Nam id semper augue. Pellentesque facilisis consectetur dui, nec luctus purus cursus in. Vivamus pellentesque, lorem non gravida volutpat, purus ante ultrices purus, vitae condimentum nibh dolor rutrum massa.
//
//Donec vel pharetra lectus, vel pulvinar lacus. Donec posuere consectetur nisi, nec viverra velit. Cras sagittis turpis magna, a aliquam eros ultricies ut. Aliquam commodo accumsan massa sit amet sagittis. Curabitur ipsum ante, condimentum eget maximus et, volutpat ut nulla. Sed dignissim dignissim ultricies. Mauris lectus neque, pharetra vitae dolor sed, dignissim blandit mauris."""
//
//    override fun run(vararg args: String?) {
////        TODO("Not yet implemented")
//
//        val redditor1 = Redditor(username = "FakeUsername1", password = "myStrongPassword", email = "mySuperEmail91@mail.co", createdDate = Instant.now())
//        val redditor2 = Redditor(username = "FakeUsername2", password = "myStrongPassword", email = "mySuperEmail110@mail.co", createdDate = Instant.now())
//        val redditor3 = Redditor(username = "FakeUsername3", password = "myStrongPassword", email = "mySuperEmail111@mail.co", createdDate = Instant.now())
//        val redditor4 = Redditor(username = "FakeUsername4", password = "myStrongPassword", email = "mySuperEmail112@mail.co", createdDate = Instant.now())
//
//        println("-------------------------------------------------------------")
//        println(redditorService.save(redditor1))
//        println("-------------------------------------------------------------")
//        println(redditorService.save(redditor2))
//        println("-------------------------------------------------------------")
//        println(redditorService.save(redditor3))
//        println("-------------------------------------------------------------")
//        println(redditorService.save(redditor4))
//        println("-------------------------------------------------------------")
//
//        val subreddit1 = Subreddit(subredditName = "FakeSubreddit1", subredditDesc = "FakeSubreddit1", createdDate = Instant.now())
//        val subreddit2 = Subreddit(subredditName = "FakeSubreddit2", subredditDesc = "FakeSubreddit2", createdDate = Instant.now())
//
//        subredditService.save(subreddit1)
//        subredditService.save(subreddit2)
//
//
//
//        for (i in 0..250)
//            if (i % 2 == 0) {
//                var post = Post(
//                    postTitle = faker.address.fullAddress(),
//                    postDesc = desc,
//                    subreddit = subreddit1,
//                    owner = redditor1,
//                    creationDate = Instant.now()
//                )
//                post = postService.save(post)
//                println("----------------")
//                println("created post with id -> ${post.postId} - ${post.subreddit.subredditId}")
//                println("----------------")
//                subredditService.addPostToSubreddit(subreddit1, post)
//            } else {
//                var post = Post(
//                    postTitle = faker.address.fullAddress(),
//                    postDesc = desc,
//                    subreddit = subreddit2,
//                    owner = redditor2,
//                    creationDate = Instant.now()
//                )
//                post = postService.save(post)
//                println("----------------")
//                println("created post with id -> ${post.postId} - ${post.subreddit.subredditId}")
//                println("----------------")
//                subredditService.addPostToSubreddit(subreddit2, post)
//            }
//        }
//    }
