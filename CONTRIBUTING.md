# Contributing to Logme

## Getting Help

If you have a question about how to use `Logme`, first try the **ASK the
Experts** link in [the Developer Toolbox](https://webster.bfm.com/apps/bdtWeb/#/).

You can reach out to owners and other contributors by posting a message to the
Programmers chatroom in Symphony with the hashtag, `#Logme`.

<br>
## Versions

* Stable versions are released on an on-demand basis.
* Whenever major changes are contributed, a stable version will be created.
* **Emergency patches may be escalated to Git administrators if [core committers](README.md) are 
unavailable and the change coordinated in the
[Command Center](https://webster.bfm.com/Wiki/display/CTS/Command+Center).**

<br>
## Bugs, Enhancements, and New Features

All work in this project is tracked in JIRA.  Click
[here](https://webster.bfm.com/jira/issues/?jql=project%3DCL%20and%20component%3DLogme)
to access the JIRA project.

If you would like to submit a new bug, enhancement, or feature request, 
submit a new JIRA ticket in the project `CL` and set the
Component to `Logme`.

<br>
#### Bugs

When submitting bugs, please identify the software version in the Description of
your bug report.

It's *incredibly* helpful to be able to reproduce the problem.  Please
include a list of steps, a bit of code, and/or a zipped repository (if
possible).

<br>
## Pull Requests

Our work flow is a [typical Feature branch workflow](https://webster.bfm.com/Wiki/x/jwBGEQ),
where contributors fork the repository, make their changes on a branch, and submit a
[Pull Request](https://help.github.com/articles/using-pull-requests)
(a.k.a. "PR").  Pull requests should usually be targeted at the `master`
branch.  Your branch should be created from within the JIRA UI as a
Feature branch.


Life will be a lot easier for you (and us) if you follow this pattern
(i.e. fork, named branch, submit PR).  If you use your fork's `master`
branch directly, things can get messy.

Please include a nice description of your changes when you submit your PR;
if we have to read the whole diff to figure out why you're contributing
in the first place, you're less likely to get feedback and have your change
merged in.

If you are starting to work on a particular area, feel free to submit a PR
that highlights your work in progress (and note in the PR title that it's
not ready to merge). These early PRs are welcome and will help in getting
visibility for your fix, allow others to comment early on the changes and
also let others know that you are currently working on something.

Before wrapping up a PR, you should be sure to:

* Write tests to cover any functional changes
* Update documentation for any changed public APIs
* Add to the [`CHANGELOG.md`](CHANGELOG.md) file the ticket and description
  under a version number suffixed with -proposed

<br>
## Unit Tests

Any new changes must not cause unit test failures, and new changes
should include unit tests that cover the bug fixes or new features.
For bug fixes, we prefer unit tests that illustrate the failure before
the change, but pass with your changes.

In addition to new tests, please ensure that your changes do not cause
any other test failures.  Running the entire test suite is helpful
before you submit a pull request.  When you build the project, the test
suite will also be built.

**80% minimum overall code coverage is required for this entire project.**

<br>
## Introducing Dependencies

Before adding a new dependency, please do the following:

- clear the change with [core committers](README.md)
- make sure that the new dependency is listed in the [CHANGELOG.md](CHANGELOG.md)

<br>
## Style Guide

Please follow conventions already found within the project.

<br>
## Contribution Checklist

1. Unit tests cover at least 80% of the lines within the entire repository
2. All unit tests pass
3. All changes are listed in [CHANGELOG.md](CHANGELOG.md)
4. Relevant Wiki documentation updated
5. Pull request submitted to [core committers](README.md)

<br>
#### Example Contribution

- Fork the repo
- Keep defaults and click `Fork repository` (if you haven't already done so)
- Go to your JIRA ticket (create one if you need to) and click More >> Create branch.
- Choose your forked repository, set Branch type to Feature or Bugfix, Branch from master, and select `Create branch`.
- Make the change
- Create a pull request from your fork to the original repository's master, filling out Description and setting Reviewers to be everyone that is listed as a [Core Committer](README.md).

Accepted changes will make it into the master.  If you
 would like to
request a release, reach out to Core Committers.

*Feel free to delete your forked repository after your change was accepted by going to your fork's landing page, clicking Settings >> `Delete repository`.*
