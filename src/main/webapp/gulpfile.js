'use strict';

let argv = require('yargs').argv;
let del = require('del');
let gulp = require('gulp');
let htmlmin = require('gulp-htmlmin');
let gulpif = require('gulp-if');
let jshint = require('gulp-jshint');
let minifycss = require('gulp-minify-css');
let rev = require('gulp-rev');
let runSequence = require('run-sequence');
let server = require('karma').Server;
let stylish = require('jshint-stylish');
let templateCache = require('gulp-angular-templatecache');
let uglify = require('gulp-uglify');
let usemin = require('gulp-usemin');

/**
 * Default task
 *      Runs gulp build
 */
gulp.task('default', (done) => {
    runSequence('build', function() {
        done();
    })
});

/**
 * Build Task
 *      Cleans the dist and rebuilds files
 */
gulp.task('build', (done) => {
    runSequence('cleanup', 'resources', 'usemin', function() {
        done();
    })
});

/**
 * Clean-Up Task
 *      Deletes the dist directory and the generated index file
 */
gulp.task('cleanup', () => {
    return del(['./dist', './index.html']);
});

/**
 * Develop Task
 *      Automatically watches files and rebuilds if there is a change
 */
gulp.task('develop', (done) => {
    gulp.watch('./source/**', ['build']);
    console.log('Develop is running! Make some changes in ./source/');
    console.log('CTRL^C to exit');
});

/**
 * Resources Task
 *      Generates the fonts and images folders in the dist
 */
gulp.task('resources', () => {
    gulp.src('./bower_components/font-awesome/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    gulp.src('./bower_components/bootstrap/dist/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    return gulp.src('./source/images/*.*')
        .pipe(gulp.dest('./dist/images'));
});

/**
 * Code Quality Task
 */
gulp.task('jshint', (done) => {
    gulp.src(['./source/scripts/**/*.js', '!./source/scripts/modules/templates/*.js'])
        .pipe(jshint())
        .pipe(jshint.reporter(stylish));
    done();
});

/**
 * Generate the template-cache file
 */
gulp.task('templates', () => {
    return gulp.src('./source/scripts/**/*.html')
    //.pipe(htmlmin({ collapseWhitespace: true }))
        .pipe(templateCache({
            module: 'mllApp.templates',
            standalone: true,
            filename: 'templates.module.js',
            // Use the appropriate line below depending on your operating system.
            // UNCOMMENT THIS LINE FOR LINUX OR MAC
            transformUrl: (url) => { return url.slice(url.lastIndexOf('/') + 1); }
             //UNCOMMENT THIS LINE FOR WINDOWS
            //transformUrl: (url) => { return  url.substr(url.lastIndexOf('\\') + 1); }
        }))
        .pipe(gulp.dest('./source/scripts/modules/templates/'));
});

/**
 * Test-runner Task
 */
gulp.task('test', function (done) {
    new server({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true
    }, done()).start();
});

/**
 * Usemin Task
 *      Runs the usemin library to consolidate all the source files.
 *      Then generates index files with references to the current build.
 */
gulp.task('usemin', ['templates'], function () {
    return gulp.src('./source/index.html')
        .pipe(usemin({
            cssBootstrap: [minifycss(), rev()],
            cssCustom: [minifycss(), rev()],
            jsJQuery: [gulpif(argv.prd, uglify()), rev()],
            jsBootstrap: [gulpif(argv.prd, uglify()), rev()],
            jsAngular: [gulpif(argv.prd, uglify()), rev()],
            jsTemplates: [gulpif(argv.prd, uglify()), rev()],
            jsCustom: [rev()]
        }))
        .pipe(gulp.dest('./'));
});
