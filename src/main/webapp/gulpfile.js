'use strict';

let del = require('del');
let gulp = require('gulp');
let htmlmin = require('gulp-htmlmin');
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
 * Build Task
 *      Cleans the dist and rebuilds files
 */
gulp.task('build', (done) => {
    runSequence('cleanup', ['fonts', 'templates'], 'usemin', function() {
        done();
    })
});

/**
 * Clean-Up Task
 *      Deletes the dist directory and the generated index file
 */
gulp.task('cleanup', (done) => {
    del(['./dist', './index.html']);
    done();
});

/**
 * Develop Task
 *      Automatically watches files and rebuilds if there is a change
 */
gulp.task('develop', (done) => {
    gulp.watch('./source/*.*', { interval: 1000 }, ['build']);
    console.log('Develop is running! Make some changes in ./source/');
    console.log('CTRL^C to exit');
});

/**
 * Fonts Task
 *      Generates the fonts folder in the dist
 */
gulp.task('fonts', () => {
    gulp.src('./bower_components/font-awesome/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    gulp.src('./bower_components/bootstrap/dist/fonts/**/*.{ttf,woff,eof,svg}*')
        .pipe(gulp.dest('./dist/fonts'));
    gulp.src('./source/fonts/*.*')
        .pipe(gulp.dest('./dist/fonts'));
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
            /*transformUrl: (url) => url.slice(url.lastIndexOf('\\') + 1),*/
            transformUrl: (url) => {
                return  url.substr(url.lastIndexOf('\\') + 1);
            }
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
    }, done).start();
});

/**
 * Usemin Task
 *      Runs the usemin library to consolidate all the source files.
 *      Then generates index files with refenrences to the current build.
 */
gulp.task('usemin', ['templates'], function () {
    return gulp.src('./source/index.html')
        .pipe(usemin({
            cssBootstrap: [minifycss(), rev()],
            cssCustom: [minifycss(), rev()],
            jsJQuery: [/*uglify(), */rev()],
            jsBootstrap: [/*uglify(), */rev()],
            jsAngular: [/*uglify(), */rev()],
            jsTemplates: [/*uglify(), */rev()],
            jsCustom: [rev()]
        }))
        .pipe(gulp.dest('./'));
});
