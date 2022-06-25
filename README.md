# KMM-Sample
KMM-Sample


# 1- Shared Module 

open shared module using android studio , write your code , business logic.

### build android aar file: 

open terminal on shared module path.
execute ./gradlew :shared:bundleReleaseAar

copy the file share-release.aar from shared/build/outputs/aar/shared-release.aar
past it on android/app/libs

sync android module 
run and enjoy 




### build ios Framework  file: 

open terminal on shared module path.

if you want run the app on simulator 


execute ./gradlew :shared:linkPodReleaseFrameworkIosX64
copy the file share.framework , shared.framework.dSYM from shared/build/bin/iosX64/podReleaseFramework/
past it on ios/shared


if you want run the app on real iphone device 


execute ./gradlew :shared:linkPodReleaseFrameworkIosArm64
copy the file share.framework , shared.framework.dSYM from shared/build/bin/iosArm64/podReleaseFramework/
past it on ios/shared

sync android module 
run and enjoy 


