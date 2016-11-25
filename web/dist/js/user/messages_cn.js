/**
 * Created by clouder on 9/25/16.
 */

jQuery.extend(jQuery.validator.messages, {
    required: "提示：必填字段",
    // remote: "提示：账户已存在",
    email: "提示：请输入正确格式的电子邮件",
    url: "提示：请输入合法的网址",
    date: "提示：请输入合法的日期",
    dateISO: "提示：请输入合法的日期 (ISO).",
    number: "提示：请输入合法的数字",
    digits: "提示：只能输入整数",
    creditcard: "提示：请输入合法的信用卡号",
    equalTo: "提示：密码不一致",
    accept: "提示：请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符"),
    minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符"),
    rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为{0} 的值"),
    min: jQuery.validator.format("请输入一个最小为{0} 的值")
});
