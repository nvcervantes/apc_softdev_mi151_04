<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\commuter */

$this->title = 'Create Commuter';
$this->params['breadcrumbs'][] = ['label' => 'Commuters', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="commuter-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
