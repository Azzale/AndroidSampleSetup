# Notes on building Android applications

![filtered 150% right](android.png)

---

# The pieces

- Efficiency
- Architecture
- Communication

---

# Efficiency

- Libraries
- Separate Java project
- Multidexing
- Cutting edge
- Testing

---

# Libraries and services

- Gradle
- RxJava and RxAndroid
- Dagger 2
- Retrolambda (until Jack and Jill arrives)
- Fabric and Crashlytics
- Firebase?
- DataBinding

![right filtered 120%](rxjava.png)

---

# Separate Java project

*Pros*
- Allows running code on the JVM
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
- Android Weekly

![right fit filtered 160%](Tweety.png)


---
# Architecture

- RX
- MVP

---
# Rx

- Learn
- Threading
- Begin with basics
- Don't use features without understanding them

![right filtered 120%](rxjava.png)

---

# Observables

Reactive programming is programming with asynchronous data streams.

- An Observable is a Promise++

---

Scary diagram

![center original 130%](marble.png)

---

Old way

``` java

private class GetStuffTask
            extends AsyncTask<String, Void, Stuff> {

  protected Stuff doInBackground(String... someData) {
    String parameter = params[0];
    Stuff stuff = StuffService.getStuff(parameter);
    return stuff;
  }

  protected void onPostExecute(Stuff user) {
    // Use the stuff
  }
}

```

---

New way


``` java
StuffService
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Subscriber<Stuff>() {
      @Override public void onCompleted() {
        // Everything has been completed
      }

      @Override public void onError(Throwable e) {
        // handle the error
      }

      @Override public void onNext(Stuff user) {
        // Use the stuff
      }
    });
```

---

#Why is this better?

- Error handling
- Memory leaks
- Chaining network calls

---
Handle your subscriptions

```java

private CompositeSubscription comsub =
          new CompositeSubscription();
comsub.add(sub1);
comsub.add(sub2);
comsub.add(sub3);
//clear all subscription on onDestroy
@Override    
public void onDestroy()
{        
     super.onDestroy();       
     comsub.clear();    
}

```

---

# MVP

![filtered 80%](MVP.png)

---

# MVP

- Activities/Fragments implements views
- See project for sample implementation
- [https://github.com/Arnesen/AndroidSampleSetup.git](https://github.com/Arnesen/AndroidSampleSetup.git)

---

# Communication

- Libraries
- Third party dependencies
- HTTP Communication
- Caching
- Mocked data
- Pull vs Push

---

# Libraries

- Retrofit
- OkHttp
- Gson (Jackson if needed)

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

# Other

- Kotlin
- Espresso UI testing
- Firebase and Data sync
