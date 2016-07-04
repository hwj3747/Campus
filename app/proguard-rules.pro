# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\hwj3747\AppData\Local\Android\sdk1/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-keepattributes Signature
-dontpreverify

#不混淆输入的类文件 ,使用该属性，不能生成mapping文件
#-dontobfuscate

#不优化输入的类文件
#-dontoptimize

-verbose

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!field/removal/writeonly,!field/marking/private,!class/merging/*,!code/allocation/variable

#保护anotation的标签属性
-keepattributes Annotation

#保护js的函数属性
-keepattributes JavascriptInterface
#Gson 保护内部类
-keepattributes EnclosingMethod

-keepattributes InnerClasses
#-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod
-keepattributes SourceFile, LineNumberTable
-keep,allowshrinking,allowoptimization class * { <methods>; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
native <methods>;
}

-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}

-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}

-assumenosideeffects class android.util.Log {
      public static boolean isLoggable(java.lang.String,int);
      public static int v(...);
      public static int i(...);
      public static int w(...);
      public static int d(...);
      public static int e(...);
}