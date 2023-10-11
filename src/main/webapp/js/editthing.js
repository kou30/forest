document.getElementById('ExecuteEditShinamono').addEventListener('submit', function(event) {
	
    var selectedValue = document.getElementById('bunruiSelect').value;
    if (selectedValue === '0') {
        alert('分類を選択してください');
        event.preventDefault(); // フォームの送信をキャンセル
        return; // フォームの送信をキャンセルした場合、ここで処理を終了します
    }

    var categoryValue = document.getElementById('nextOptionSelect').value;
    if (categoryValue === '0') {
        alert('項目選択を選択してください');
        event.preventDefault();
        return;
    }

    var itemValue = document.getElementById('thirdOptionSelect').value;
    if (itemValue === '0') {
        alert('詳細項目選択を選択してください');
        event.preventDefault();
        return;
    }

    // 品目名のバリデーション
    var shinamonoName = document.getElementsByName('SHINAMONONAME')[0].value;
    if (shinamonoName.trim() === '') {
        alert('品目名を入力してください');
        event.preventDefault();
        return;
    }

    // 金額のバリデーション
    var kingaku = document.getElementsByName('KINGAKU')[0].value;
    if (isNaN(kingaku) || kingaku < 0) {
        alert('金額を正しい形式で入力してください');
        event.preventDefault();
        return;
    }

    // 備考のバリデーション
    var memo = document.getElementsByName('MEMO')[0].value;
    if (memo.trim() === '') {
        alert('備考を入力してください');
        event.preventDefault();
        return;
    }

    // 絵文字のバリデーション
    var emojiRegex = /[\uD800-\uDBFF][\uDC00-\uDFFF]/g;
    if (emojiRegex.test(memo) || emojiRegex.test(shinamonoName)) {
        alert('品目名と備考に絵文字を使用しないでください');
        event.preventDefault();
        return;
    }

    // 文字数のバリデーション
    if (memo.length > 100) {
        alert('備考は100文字以内で入力してください');
        event.preventDefault();
        return;
    }

    // ここで他のバリデーションルールを追加

    // バリデーションが成功した場合、フォームが送信されます
});


