# Changelog

## Release 2.10 (unreleased)
Brief summary:

### Breaking changes

---

## Release 2.9 (11/13/2025)
Breief summary:
- Created new Objects for Single-Cell & Single-Nuc data
- Created new Endpoints to retrieve new SN / SC data
- Dynamic count returning for cluster counts
- Use a Union instead of Union All for ClusterHierarchy Endpoint
- Use a Set for ClusterHierarchy

### Breaking changes
N/A

---

## Release 2.8 (6/30/2025)
Brief summary:
- Updates to how we calculate the numbers for the tables so it is more data driven

---

## Release 2.7 (SKIPPED)
There happened to be nothing of note to release for the 2.7 release of the other Atlas apps, so we decided to skip this version number to keep all the version numbers in sync.

---

## Release 2.6 (12/19/2024)
Brief summary of what's in this release:
- Added Primary adjudicated category to the participant summary
- Modified participant summary endpoint to remove "clinicalData" section in favor of individual elements
- Changed tables to refer to enrollment category rather than tissue type

### Breaking changes
- This version won't work with previous versions of the database because we:
  - moved where/how we store clinical data for a participant
  - changed the tissue type columns to enrollment category
- This version won't work with previous versions of front end apps because we:
  - changed the format of the data returned to refer to enrollment category rather than tissue type
  - changed the format of the data returned for participant summary 

---

## Release 2.5 (10/3/2024)
- Java upgrade

### Breaking changes
None

### Non-breaking changes
None

----

## Release 2.5 (10/3/2024)
- Java upgrade

### Breaking changes
None

### Non-breaking changes
None

------
## Release 2.4 (released 07/08/2024)
Brief summary of what's in this release:
- added a total column to show total number of files for a grouping (needed back end because total includes groups not shown on atlas home page or explorer home page)

### Breaking changes

Breaking changes include any database updates needed, if we need to edit any files on system (like .env or certs, etc). Things that are outside of the code itself that need changed for the system to work.


### Non-breaking changes

Just a place to keep track of things that have changed in the code that we may want to pay special attention to when smoke testing, etc.
