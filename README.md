# Pokemon-app
## Architecture
- MVVM (Model-View-Viewmodel)
## Dependencies
- [Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [The paging library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [The navigation library](https://developer.android.com/guide/navigation)
- [Glide](https://bumptech.github.io/glide/)
## Notes
- Room is used to cache the details screen data.
  - The response gets cached for 24 hours.
  - The database is the source of truth. So, the response will be served from the database always.
- Regarding the flavor_text, I parsed all the english entries and displayed them in a recycler view.
