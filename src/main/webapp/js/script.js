/** 
* Filename: script.js
* 
* Description: 
* このjsファイルは品物の選択肢の機能を提供するためのものです。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-04 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

bunruiSelect.addEventListener('change', function () {
    const selectedBunrui = bunruiSelect.value;

    // 第一分類が選択されたら第二分類を有効化
    nextOptionSelect.disabled = false;
    nextOptionSelect.value = '0';
    thirdOptionSelect.disabled = true;
    thirdOptionSelect.value = '0';

    // 第二分類が選択されたら第三分類を有効化
    nextOptionSelect.addEventListener('change', function () {
        const selectedNextOption = nextOptionSelect.value;
        if (selectedNextOption !== '0') {
            thirdOptionSelect.disabled = false;

            if (selectedNextOption === '1') { // 第二分類が「品物」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: 'お中元' },
                    { value: '2', label: 'お供え物' },
                    { value: '3', label: 'お祝い品' },
                    { value: '4', label: 'お見舞い品' },
                    { value: '5', label: '贈答品' },
                    { value: '6', label: 'お土産' },
                    { value: '7', label: '記念品' },
                    { value: '8', label: '誕生日プレゼント' },
                    { value: '9', label: '結婚祝い品' },
                    { value: '10', label: '出産祝い品' },
                    { value: '11', label: '引っ越し祝い品' },
                    { value: '12', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                 
                amountField.style.display = 'none'; 
            } else if (selectedNextOption === '2') { // 第二分類が「お金」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: '寄付金' },
                    { value: '2', label: '贈与金' },
                    { value: '3', label: '祝儀金' },
                    { value: '4', label: '贈答金' },
                    { value: '5', label: '報酬金' },
                    { value: '6', label: '賞金' },
                    { value: '7', label: '贈賄金' },
                    { value: '8', label: '支援金' },
                    { value: '9', label: '貢献金' },
                    { value: '10', label: 'ギフト券' },
                    { value: '11', label: '仏教関連金' },
                    { value: '12', label: 'お祝い金' },
                    { value: '13', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                 
                amountField.style.display = 'block'; // 金額コメント欄を表示
            } else if (selectedNextOption === '3') { // 第二分類が「手紙など」の場合、第三分類を更新
                const thirdOptions = [
                    { value: '0', label: '選択してください' },
                    { value: '1', label: '年賀状' },
                    { value: '2', label: '招待状' },
                    { value: '3', label: 'お詫び状' },
                    { value: '4', label: '通知状' },
                    { value: '5', label: 'その他' }
                ];
				
                thirdOptionSelect.innerHTML = '';
                thirdOptions.forEach(option => {
                    const optionElement = document.createElement('option');
                    optionElement.value = option.value;
                    optionElement.textContent = option.label;
                    thirdOptionSelect.appendChild(optionElement);
                });
                
                amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
            } else { // その他の場合はデフォルトの選択肢を表示
                thirdOptionSelect.innerHTML = '<option value="0">選択してください</option>';
                amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
            }
        } else {
            thirdOptionSelect.disabled = true;
            thirdOptionSelect.value = '0';
            amountField.style.display = 'none'; // 第二セレクト変更時に金額コメント欄を非表示に
        }
    });
});


// 初期ロード時に第一分類の選択肢に応じて第二分類を設定
const selectedBunrui = bunruiSelect.value;
if (selectedBunrui === '1' || selectedBunrui === '2') {
    nextOptionSelect.disabled = false;
} else {
    nextOptionSelect.disabled = true;
}

