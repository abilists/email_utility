# email_utility library for <a href="http://www.abilists.com" ><img src="https://github.com/abilists/abilists_client/blob/master/src/main/webapp/static/apps/img/abilists/logo01.png" height="22" alt="Abilists"></a>

[![Build Status](https://travis-ci.org/abilists/email_utility.svg?branch=master)](https://travis-ci.org/abilists/email_utility)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/abilists/email_utility)

**email_utility** is to send emails to you on Abilists. 

Utility_sample is:

  * Free
  * Easy to use

## About
api_utility has a few special features:

* Email: Send
* EmailBean: 
* Emailer: 

## Runtime Requirements

- *P1:* Java8 or newer
- *P2:* Junit test

## How to Install
Build as blow
```
$ gradle install
```

## Get started
Add the following code into the Model class.
```
EmailBean email = new EmailBean();
email.setSmtpHost("smtp.gmail.com");
email.setSmtpPort("587");
email.setTo("abilists@gmail.com");
email.setSmtpSender("heyguybug@gmail.com");
email.setSmtpAuthEnable("true");
email.setSmtpStarttlsEnable("true");

RequestTemplateModel tempModel = new RequestTemplateModel();
tempModel.setReqEmailAddress(requestPara.getEmailAddress());
tempModel.setReqName(requestPara.getName());
tempModel.setReqContents(requestPara.getContents());

// Get the real path on Tomcat.
String basePath = servletContext.getRealPath("");

StringBuffer sbBodyPath = new StringBuffer();
sbBodyPath.append(basePath).append("/template/email");

String body = TemplateUtility.mergeTemplate(sbBodyPath.toString(), "request.ftlh", tempModel);

email.setMsg(body);
email.setSubject("title");

Emailer.sendEmail(email, "id", "password");
```

## License
security_utility is distributed under the MIT License.
