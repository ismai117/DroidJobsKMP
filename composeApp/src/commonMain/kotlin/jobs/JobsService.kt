package jobs


import droidjobskmp.composeapp.generated.resources.Res
import droidjobskmp.composeapp.generated.resources.chip
import droidjobskmp.composeapp.generated.resources.ocado
import droidjobskmp.composeapp.generated.resources.sky
import droidjobskmp.composeapp.generated.resources.starling
import droidjobskmp.composeapp.generated.resources.winnow
import org.jetbrains.compose.resources.ExperimentalResourceApi


const val bulletPoint = "\u2022"

@OptIn(ExperimentalResourceApi::class)
class JobsService {

    private val roles = listOf(
        Jobs(
            id = "f47ac10b-58cc-4372-a567-0e02b2c3d479",
            title = "Android Engineer",
            role = "Mobile Developer",
            companyLogo = Res.drawable.starling,
            company = "Starling Bank",
            companyMotto = "A better bank for everyone",
            aboutUs = """
                Starling is a leading digital bank on a mission to disrupt the banking industry.

                Since our launch in 2014, we've surpassed 2 million accounts, including over 350,000 business accounts. Our total deposits, meanwhile, have topped £5 billion and we have lent over £2bn over the same period. We're a fully licensed UK bank, and we have the culture and spirit of a fast-moving, disruptive technology company. We've won the Best British Bank award four years running, and now employ over 1500 people across our London, Southampton, Cardiff & Dublin offices.

                Starling Bank is an equal opportunity employer, and we’re proud of our ongoing efforts to foster diversity & inclusion in the workplace. Individuals seeking employment at Starling Bank are considered without regard to race, religion, national origin, age, sex, gender, gender identity, gender expression, sexual orientation, marital status, medical condition, ancestry, physical or mental disability, military or veteran status, or any other characteristic protected by applicable law.

                By submitting your application, you agree that Starling Bank may collect your personal data for recruiting and related purposes. Our Privacy Notice explains what personal information we may process, where we may process your personal information, its purposes for processing your personal information, and the rights you can exercise over our use of your personal information.
            """.trimIndent(),
            city = "London",
            country = "UK",
            address = "Finsbury Ave, London EC2M, UK",
            industry = "FinTech · Banking · Finance",
            description = """
                Starling engineers love building things, creating new stuff, learning new technologies and working with others across all areas of the business to bring brilliant products and features to life. We enjoy working with engineers who are excited about helping us deliver new features, regardless of what their primary tech stack may be. Hear from our engineers in our latest blogs or our case studies with Women in Tech.

                Our mobile engineers work in cross-functional feature teams arranged across broader engineering groups. You are empowered to make the decisions necessary for the platform and to provide insight to the team leads on such projects. You’ll also be working closely with other Android engineers with a variety of experience levels and interests, helping us improve and develop our Android app, CI automations and tooling. You’ll be committing, reviewing and shipping new code right from the first week! 🎉

                In the Starling Android application we are proud to be working with some of the latest technologies in the industry. When adopting new technologies we always consider as a team whether it’s the best choice for our product and our users. We have a strong collaborative and open culture here so you’ll find support outside your team too.
            """.trimIndent(),
            experienceLevel = "Mid, Senior",
            skills = listOf(
                "Kotlin",
                "Android",
                "Java",
                "Mobile Development",
                "RxJava",
                "API Security",
                "AWS",
                "Dagger",
                "JUnit"
            ),
            salary = "Undisclosed",
            companyBenefits = """
                33 days holiday (including flexible bank holidays).
                An extra day’s holiday for your birthday.
                16 hours paid volunteering time a year.
                Part-time and/or flexible hours available for most roles.
                Salary sacrifice, company enhanced pension scheme.
                Life insurance at 4x your salary.
                Hybrid/remote working.
                Private Medical Insurance with VitalityHealth including mental health support and cancer care. Partner benefits include discounts with Waitrose, Mr&Mrs Smith and Peloton.
                Generous family-friendly policies.
                Varied social groups set up and run by our employees.
                Perkbox membership giving access to retail discounts, a wellness platform for physical and mental health, and weekly free and boosted perks.
                Access to initiatives like Cycle to Work, Salary Sacrificed Gym partnerships and Electric Vehicle (EV) leasing.
            """.trimIndent(),
            employmentType = "Permanent",
            workEnvironment = "Hybrid",
            visaSponsorship = "Not available",
            postedDate = "1 month ago",
            requirements = """
                What we are looking for:

                As a fully digital bank, we are looking for engineers that are able to understand and prioritise security when implementing new features, fixing bugs or making technical improvements, across all the layers. Our team is designing, building and releasing new screens every day, for this reason we are interested in people with a particular focus on Accessibility along with writing clean and maintainable code.

                The way to thrive and shine within Starling is to be a self-driven individual and be able to take full ownership of everything around you: From building the code, shipping and maintaining it, to sharing knowledge with your colleagues and making sure all processes are efficient and productive to deliver the best possible results for our customers.

                Our aim is to deliver stable, resilient and high quality code, that is why testing is another key quality we look for when expanding our team. We useEspressoto write our UI/E2E Test andJUnitto write our Unit Tests across all layers of the application, as well as manually test all features before they go into production. We also work closely with QA engineers to ensure we have an additional level of testing when working on complex feature delivery.

                All Starling engineers work in a hybrid pattern both from home and one of our offices. Our preference is that you’re located within a commutable distance to either our London, Manchester, Southampton or Cardiff office, so that we’re able to see each other and collaborate in person too.
            """.trimIndent(),
            link = "https://cord.co/candidate/search/101581/u/starling-bank/jobs/21074-android-engineer"
        ),
        Jobs(
            id = "0f5abc9c-8c1e-4653-aa2b-972e5b716812",
            title = "Android Software Engineer",
            role = "Mobile Developer",
            companyLogo = Res.drawable.ocado,
            company = "Ocado Technology",
            companyMotto = "We're putting the world's retailers online\nusing the cloud, robotics, AI, and IoT",
            aboutUs = """
                After two decades, Ocado Group is entering an exciting new chapter in its history, while staying true to the ambitions, ideals and values that have led us to such success so far.

                As true pioneers of the online grocery market, we’ve blazed a trail through an increasingly digital world, both leading and benefiting from the fast-increasing consumer shift to online shopping. At the heart of it all lies our technological know-how and unparalleled IP.

                Our combined expertise and capabilities underpin the continued evolution of the Ocado Retail Limited platform, which has seen it become the biggest grocery retailer of its kind in the world. We're now taking those same unique qualities to help our partners around the world reach similar heights, through our Ocado Solutions business.
            """.trimIndent(),
            city = "Hatfield",
            country = "UK",
            address = "",
            industry = "E-Commerce · Robotics · Data",
            description = """
                Due to growth, we have an exciting new opportunity for an Android Software Engineer to join our Ecommerce department on a permanent basis, based in Hatfield. (Hybrid Working)

                The 5 Stars team is responsible for inspiring shoppers on the web& mobile platforms, through features like  reviews, recommendations and product information.  We’re responsible for letting experienced shoppers shop quickly whilst providing inspiration and information to those who are uncertain. The team is made up of talented Android, iOS, Web and back-end engineers, Team Lead  and works closely with members of the UX, Product, and Data teams.

                We want the successful individual to bring their passion for building polished, effective solutions to the problems we set out to tackle. Our Android shopping app is native Kotlin with an MVVM architecture, backed by a Java microservice backend.
            """.trimIndent(),
            experienceLevel = "Mid",
            skills = listOf(
                "Android",
                "Kotlin",
                "MVVM"
            ),
            salary = "Undisclosed",
            companyBenefits = """
                Our employee benefits are designed for you, we care about people and we’ve ensured we have a wealth of benefits that focus on your well-being. We regularly review our benefits to ensure we are supporting our employees appropriately. Currently, we offer technically stretching work, a competitive salary and:

                Hybrid working patterns meaning part of the working week can be spent working remotely (typically 3 days per week). However, your working pattern will depend upon your role/team.
                30 days working from anywhere policy.
                Wellbeing support through Apps such as Unmind and an Employee Assistance Programme.
                25 days annual leave, rising to 27 days after 5 years service (plus optional holiday purchase).
                Pension scheme (various options available including employer contribution matching up to 7%).
                Private Medical Insurance.
                22 weeks paid maternity leave and 6 weeks paid paternity leave (once relevant service requirements complete).
                Train Ticket loan (interest-free).
                Cycle to Work Scheme.
                Free shuttle bus to and from Hatfield Train Station to the Hatfield offices.
                Free shuttle bus to and from Welwyn Garden City Train Station to the Welwyn Garden City offices.
                Opportunity to participate in Sharesave and Buy as You Earn share schemes.
                15% discount on Ocado.com and free delivery for all employees.
                Income Protection (can be up to 50% of salary for 3 years).
                Life Assurance (3 x annual salary).
               
                We also have regular divisional socials, sports clubs not to mention the Ocado Technology Academy for a packed schedule of courses, conferences and events. If you think you have what it takes to make a difference, please submit your application below.
            """.trimIndent(),
            employmentType = "Permanent",
            workEnvironment = "Hybrid",
            visaSponsorship = "Available",
            postedDate = "1 Month ago",
            requirements = """
                You have great programming and technical design skills.
                You have an extensive knowledge of the Android environment from developing high-quality apps.
                Building production apps with Kotlin.
                Taking pride in writing polished and fully-tested code.
                Passionate about great UX.
                You create user experiences with accessibility as a core requirement.
                Your collaboration skills allow you to work effectively alongside a cross-functional team.
                A desire to share knowledge and learn from others in order to improve your breadth and depth of knowledge.
            """.trimIndent(),
            link = "https://cord.co/candidate/search/101581/u/ocado-technology/jobs/26549-android-software-engineer"
        ),
        Jobs(
            id = "43e2f3e1-f2e1-48b7-ae05-1a2d61c3bf44",
            title = "Senior Android Developer",
            role = "Mobile Developer",
            companyLogo = Res.drawable.sky,
            company = "Sky",
            companyMotto = "Make [better] happen | Tech, Product & Data Careers at Sky",
            aboutUs = "Sky is one of Europe’s leading media and entertainment companies and we are proud to be part of Comcast Corporation. Across six countries our innovative products connect 23 million customers to the best apps, and all the entertainment, sports, news and arts they love, including our own award-winning original content.",
            city = "Isleworth TW7",
            country = "UK",
            address = "Isleworth TW7",
            industry = "Media and Streaming",
            description = """
                We believe in better. And we make it happen. Better content. Better products. And better careers.

                Working in Tech, Product or Data at Sky is about building the next and the new. From broadband to broadcast, streaming to mobile, SkyQ to Sky Glass, we never stand still. We optimise and innovate.

                We turn big ideas into the products, content and services millions of people love.

                And we do it all right here at Sky.

                "The Core Client Team is responsible for building the playback-related functionality in Sky’s OTT products. We work closely with the teams in NBCUniversal’s Peacock, Sky Go, NOW TV, Sky Sports and others to ensure that common functionality is built in a reliable, scalable and efficient way. We own the internally open source SDK which our colleagues across Sky use to achieve effortless playback on Windows, macOS, Android, iOS, Samsung TV, LG TV, PlayStation, Xbox and Roku. The SDK abstracts the complexity of achieving playback on these devices and provides a single, simple to use API. We are now looking for talented engineers to join us in finding new innovative ways to build features and improve upon our video players"- says the Engineering Manager
            """.trimIndent(),
            experienceLevel = "Senior",
            skills = listOf(
                "Android",
                "Kotlin"
            ),
            salary = "£75,000 - £90,000",
            companyBenefits = """
                Sky Q, for the TV you love all in one place.
                The magic of Sky Glass at an exclusive rate.
                A generous pension package.
                Private healthcare.
                Discounted mobile and broadband.
                A wide range of Sky VIP rewards and experiences.
            """.trimIndent(),
            employmentType = "Permanent",
            workEnvironment = "Hybrid",
            visaSponsorship = "Not available",
            postedDate = "1 month ago",
            requirements = """
                Build new features and maintain existing solutions within the SDK.
                Contribute to planning and architecture of new features and see them through to release.
                Daily code reviews on new submissions raised to the SDK, both from colleagues within the team and externally.
                Support client teams with integration of the SDK and assist in diagnosing issues.
                A strong understanding of software design and development principles.
                An expert-level understanding of Java. Kotlin experience is ideal.
                Experience of Video Playback technology.
                Ability to communicate effectively and explain complex problems in a simple way.
                Enjoy collaborating with others, both inside and outside the team.
                Ability to provide constructive and objective feedback.
            """.trimIndent(),
            link = "https://cord.co/candidate/search/101581/u/sky/jobs/23283-senior-android-developer"
        ),
        Jobs(
            id = "675f41e2-d7a2-4ee6-8238-b0ee34729baf",
            title = "Senior Android Engineer",
            role = "Mobile Developer",
            companyLogo = Res.drawable.chip,
            company = "Chip",
            companyMotto = "The savings and investments app of the future.",
            aboutUs = "At Chip, our mission is to make our customers wealthier. An app to build your long-term wealth across investment funds and savings accounts.",
            city = "",
            country = "UK",
            address = "Remote only",
            industry = "FinTech",
            description = """
                We are looking for a Senior Android Engineer who possesses a passion for pushing mobile technologies to the limits. This Android app developer will work with our team of talented engineers to design and build the next generation of our mobile applications.

                As a Senior Developer, you'll be at the core of Android delivery at Chip. You specialise in Android but use your much broader technical experience to help steer the team.

                You'll be working with one of the fastest-growing fintechs, helping us define how we use mobile to drive our business and help make saving effortless for millions. You'll share your passion and experience with those around you, allowing and encouraging your less experienced teammates to step up to the challenges. At a senior level, your responsibility reaches beyond writing code. Your mobile teammates will naturally look to you for on-the-job coaching and broader career advice
            """.trimIndent(),
            experienceLevel = "Senior",
            skills = listOf(
                "Android",
                "Kotlin",
                "Mobile Development",
                "Agile"
            ),
            salary = "Undisclosed",
            companyBenefits = """
                Discretionary share option bonus every 6 months.
                Workplace pension scheme (Employer: 3% / Employee: 4% / Tax Relief: 1% / Total: 8%).
                We are an equal-opportunity employer and value diversity.
                Flexible working arrangements.
                Unlimited holiday (policy not to count).
                £1500 Annual Chip Scholarship fund to support your personal development.
                MacBook Pro or similar.
            """.trimIndent(),
            employmentType = "Permanent",
            workEnvironment = "Remote",
            visaSponsorship = "Not available",
            postedDate = "2 weeks ago",
            requirements = """
                Design, build and ship features for our Android application from inception to release.
                Collaborate with cross-functional teams to deliver a high-quality product.
                Working across the engineering team to help shape the product roadmap.
                Build reusable tools to enhance our development practices.
                Owning large architectural changes to the codebase.
                Being seen as a point of contact to other areas of the business and can communicate effectively with non-technical stakeholders.
                You're a passionate and self-motivated developer.
                You've built an Android application that's on the Google Play Store.
                Strong working knowledge of Kotlin and Android SDKs.
                You are committed to writing high quality, testable code.
                You love collaborating as an Agile team.
                Solid understanding of the mobile development lifecycle.
                Have experience in mentoring other developers and delegating appropriate tasks.
            """.trimIndent(),
            link = "https://chip.teamtailor.com/jobs/3512485-senior-android-engineer-remote-within-the-uk?utm_source=cord"
        ),
        Jobs(
            id = "e23eaf45-fab7-47a4-9f06-76e2b517c9f1",
            title = "Android Developer",
            role = "Mobile Developer",
            companyLogo = Res.drawable.winnow,
            company = "Winnow",
            companyMotto = "Sustainable commercial food waste solutions.",
            aboutUs = "At Winnow, we help the food service and hospitality industry cut down on food waste by making the kitchen smarter. With Winnow Vision, our AI-enabled tool. kitchens can automatically track food waste, cut costs and save time.",
            city = "London",
            country = "UK",
            address = "",
            industry = "Software Development",
            description = "We are looking for an Android Developer who possesses a passion for pushing mobile technologies to the limits. This Android app developer will work with our team of talented engineers to design and build the next generation of our mobile applications. Android programming works closely with other app development and technical team",
            experienceLevel = "Junior, Mid, Senior",
            skills = listOf(
                "Agile",
                "Android",
                "Git",
                "IoT",
                "Java",
                "Kotlin",
                "MVVM"
            ),
            salary = "Undisclosed",
            companyBenefits = """
                Competitive base salary plus Sales commission.
                Company stock options package.
                Pension scheme.
                Eye care vouchers.
                Life insurance.
                Company part-funded health insurance.
                2 Wellness hours per month, plus a £40 monthly wellness allowance.
                Early finish Friday - log off at 3pm if you've completed your work by then!.
                Employee Assistance Programme - 24/7 helpline for your wellbeing.
                25 days of paid vacation time (plus the option to buy a further 5 days annual leave) in addition to national holidays.
                You will love what you do – waking up every day solving one of the biggest social problems of our generation.
                Committed team members with broad experience who share a common passion to build a world class business.
            """.trimIndent(),
            employmentType = "Permanent",
            workEnvironment = "Hybrid",
            visaSponsorship = "Not available",
            postedDate = "3 weeks ago",
            requirements = """
                Technical Qualifications:

                Reactive Extensions for the Java Virtual Machine.
                applying SOLID principles into development flow.
                experience in the 3 tiers of Android test.
                experience in software feature development in a Continuous Integration environment.
                Java 8 features and garbage collection principles in JVM.
                understanding Event Driven Programming.
                proficient understanding of code versioning tools such as Git and BitBucket.
                managing data in relational database systems.
                experience in using Android support library.
                experience in writing solutions using MVVM + Data Binding.
                seeing unit tests as an essential part of your work to complete your given tasks.
                Clean architecture / Use case driven feature implementation.
                Kotlin / Coroutines / Flows.

                Optional role requirements:
                
                operational knowledge of the following libraries: Retrofit, OkHttp3, Dagger2, Room, Fresco, Hilt.
                working with IoT devices and writing communication layers for USB and Bluetooth devices (optional).
                creating complex layouts for large screen Android devices.
                writing and updating technical documentation using Confluence.

                About you:
                
                BS/MS degree in Computer Science, Engineering or a related subject.
                experience working as a cross-functional agile team member.
                proficiency with Android SDK.
                knowledge of object-oriented analysis and design.
                well spoken and written English language.

                Personal traits:
                
                you write clean&maintainable code.
                you see yourself as a part of a high-performance team that delivers results.
                you are confident of your programming skills.
                you don't mind asking if you are not certain and researching topics on your own.
                you are not afraid to build something to prove a point.
                you enjoy helping others.
                you see no end in self-improvement.
                you see algorithms in food recipes.
                you can provide and receive constructive feedback.
            """.trimIndent(),
            link = "https://apply.workable.com/winnow/j/6B401380BE/?utm_source=cord"
        )
    )

    fun getAllJobs(): List<Jobs> {
        return roles
    }

    fun getJob(jobID: String): Jobs? {
        return roles.find { it.id == jobID }
    }

}