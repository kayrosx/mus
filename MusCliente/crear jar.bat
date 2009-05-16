mkdir bin\META-INF
copy META-INF\Manifest.mf bin\META-INF\Manifest.mf
cd bin
jar cvfm Mus.jar META-INF\Manifest.mf *
pause