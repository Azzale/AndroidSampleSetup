# Opinionated notes on building Android applications

![filtered 150% right](android.png)

---

# [fit]But not really

Based on popular and proven *frameworks* and *patterns*.

---

# The pieces

- Efficiency
- Communication
- Architecture

---

# Efficiency

- Libraries
- Separate Java project
- Multidexing
- Cutting edge

---

# Libraries and services

- Gradle
- RxJava and RxAndroid
- Dagger 2
- Retrolambda (until Jack and Jill arrives)
- Fabric and Crashlytics
- Firebase?


![right filtered 120%](rxjava.png)

---

# Separate Java project

*Pros*
- Allows running code directly on the JVM
- Forces a one way dependency
- Writing tests is a smoother experience

*Cons*
- Possible to depend on non Android code
- Added complexity

Large project, do it.

Small project, probably not.

---
# Multidexing

- Avoid it if possible
- The limit is 65535 references
- Select libraries to reduce method and reference count
- Examples:
  - gson over jackson (1329 vs 10770)
  - picasso over glide (849 vs 2879)

---
# Cutting edge

- Android Studio
- Stay current
- Try out the latest libraries
- UI deprecates fast

![right fit filtered 160%](Tweety.png)

---

# Communication

- Libraries
- Third party dependencies
- HTTP Communication
- Caching
- Pull vs Push

---

# Libraries

- Retrofit
- OkHttp
- Gson (Jackson if needed)
- RxJava

![right filtered 80%](square.png)

---

# Third party dependencies

- Incomplete or unstable APIs
- Project setup needs to support mocking of data

``` java

@Provides
public DataApi provideDataApi(RetroApiContainer retroApiContainer) {
    if (BuildConfig.DUMMY) {
        return new FakeDataApi();
    } else {
        return new RealDataApi(retroApiContainer);
    }
}

```

---

# HTTP Communication

``` java
private RetroApiDefinition createApiClient(OkHttpClient okHttpClient, String url) {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(url)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .client(okHttpClient)
              .build();
     return retrofit.create(RetroApiDefinition.class);
  }

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();
}

```

---
# Architecture

- Libraries
- MVP
- Views
- Presenters
- Mocked data

---

<!--
```java

private void openNewFragment(BaseFragment newFragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit);
    fragmentTransaction.replace(R.id.menu_frame_two, newFragment, newFragment.getFragmentName());
    fragmentTransaction.commit();
}


``` -->
<!-- Sometimes it’s helpful to keep some notes in your document, without having to show them in your presentation. You can use simple HTML-style commenting syntax to do this. -->

<!--
You can even skip entire slides, without having to delete your thoughts.
---
# Maybe this is an awesome slide, but then again, maybe not.
---
-->
